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
      val networkItems = service.fetchTodos()
      db.todoDao().insertTasks(networkItems)
      db.todoDao().todoObservable().toObservable()
        .subscribeOn(Schedulers.io())
        .toFlowable(BackpressureStrategy.BUFFER)
        .asFlow()
        .collect {
          itemsSubject.onNext(it)
        }
    }
  }

  fun todoUpdated(todo: Todo) = viewModelScope.launch {
    db.todoDao().insertTask(todo)
  }

  fun todoDeleted(todo: Todo) = viewModelScope.launch {
    db.todoDao().deleteTodo(todo)
  }

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }
}
