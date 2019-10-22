package alexsullivan.com.reactivetodo

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class TodoViewModel(service: TodoNetworkService, private val db: TodoDatabase) : ViewModel() {
  private val itemsSubject = BehaviorSubject.create<List<Todo>>()
  private val disposables = CompositeDisposable()
  val itemsObservable: Observable<List<Todo>> = itemsSubject.hide()

  init {
    service
      .fetchTodos()
      .flatMap { todos -> db.todoDao().insertTasks(todos) }
      .flatMapObservable { db.todoDao().todoObservable().toObservable() }
      .subscribeOn(Schedulers.io())
      .subscribe(itemsSubject::onNext)
      .addTo(disposables)
  }

  fun todoUpdated(todo: Todo) {
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
