package alexsullivan.com.reactivetodo

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.reactive.asFlow

class TodoNetworkServiceImpl(private val db: TodoDatabase) : TodoNetworkService {
  override suspend fun fetchTodos(): List<Todo> {
    val flowable = db.todoDao().todoObservable().asFlow()
    val items = flowable.first()
    return if (items.isEmpty()) {
      listOf(
        Todo(1L, "First todo", false),
        Todo(2L, "Second todo", false),
        Todo(3L, "Third todo", true),
        Todo(4L, "Fourth todo", false),
        Todo(5L, "Fifth todo", true)
      )
    } else {
      items
    }
  }
}