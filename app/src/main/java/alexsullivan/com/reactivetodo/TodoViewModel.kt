package alexsullivan.com.reactivetodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.asFlow

class TodoViewModel(service: TodoNetworkService, private val db: TodoDatabase) : ViewModel() {
  private val itemsSubject = BehaviorSubject.create<List<Todo>>()
  private val disposables = CompositeDisposable()
  val itemsObservable: Observable<List<Todo>> = itemsSubject.hide()

  init {
    viewModelScope.launch {
      service
        .fetchTodos()
        .flatMap { todos -> db.todoDao().insertTasks(todos) }
        .flatMapObservable { db.todoDao().todoObservable().toObservable() }
        .subscribeOn(Schedulers.io())
        .toFlowable(BackpressureStrategy.BUFFER)
        .asFlow()
        .collect {
          itemsSubject.onNext(it)
        }
    }
  }

  fun todoUpdated(todo: Todo) = viewModelScope.launch {
    db.todoDao()
      .insertTask(todo)
      .subscribeOn(Schedulers.io())
      .subscribe()
      .addTo(disposables)
  }

  fun todoDeleted(todo: Todo) {
    db.todoDao()
      .deleteTodo(todo)
      .subscribeOn(Schedulers.io())
      .subscribe()
      .addTo(disposables)
  }

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }
}
