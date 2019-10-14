package alexsullivan.com.reactivetodo

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

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
