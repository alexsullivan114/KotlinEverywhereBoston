package alexsullivan.com.reactivetodo

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class TodoViewModel : ViewModel() {
  private val itemsSubject = BehaviorSubject.create<List<Todo>>()
  val itemsObserable: Observable<List<Todo>> = itemsSubject.hide()

  init {
    itemsSubject.onNext(
      listOf(
        Todo("First todo", false),
        Todo("Second todo", false),
        Todo("Third todo", true),
        Todo("Fourth todo", false),
        Todo("Fifth todo", true)
      )
    )
  }
}
