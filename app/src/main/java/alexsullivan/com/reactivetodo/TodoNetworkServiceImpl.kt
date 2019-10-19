package alexsullivan.com.reactivetodo

import io.reactivex.Single

class TodoNetworkServiceImpl : TodoNetworkService {
  override fun fetchTodos(): Single<List<Todo>> {
    return Single.just(
      listOf(
        Todo(0L, "First todo", false),
        Todo(0L, "Second todo", false),
        Todo(0L, "Third todo", true),
        Todo(0L, "Fourth todo", false),
        Todo(0L, "Fifth todo", true)
      )
    )
  }
}