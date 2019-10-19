package alexsullivan.com.reactivetodo

import io.reactivex.Single

class TodoNetworkServiceImpl(private val db: TodoDatabase) : TodoNetworkService {
  override fun fetchTodos(): Single<List<Todo>> {
    return db.todoDao().todoObservable().firstOrError().map {
      if (it.isNotEmpty()) it else listOf(
        Todo(1L, "First todo", false),
        Todo(2L, "Second todo", false),
        Todo(3L, "Third todo", true),
        Todo(4L, "Fourth todo", false),
        Todo(5L, "Fifth todo", true)
      )
    }
  }
}