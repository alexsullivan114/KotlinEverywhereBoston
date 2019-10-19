package alexsullivan.com.reactivetodo

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class TodoViewModel(private val service: TodoNetworkService) : ViewModel() {
  private val itemsSubject = BehaviorSubject.create<List<Todo>>()
  private val disposables = CompositeDisposable()
  val itemsObservable: Observable<List<Todo>> = itemsSubject.hide()

  init {
    service
      .fetchTodos()
      .subscribeOn(Schedulers.io())
      .subscribe(itemsSubject::onNext)
      .addTo(disposables)
  }

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }
}
