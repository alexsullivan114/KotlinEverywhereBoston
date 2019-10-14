package alexsullivan.com.reactivetodo

import io.reactivex.rxjava3.core.Single
import java.util.UUID

class TodoNetworkServiceImpl : TodoNetworkService {
  override fun fetchTodos(): Single<List<Todo>> {
    return Single.just(
      listOf(
        Todo(UUID.randomUUID().toString(), "First todo", false),
        Todo(UUID.randomUUID().toString(), "Second todo", false),
        Todo(UUID.randomUUID().toString(), "Third todo", true),
        Todo(UUID.randomUUID().toString(), "Fourth todo", false),
        Todo(UUID.randomUUID().toString(), "Fifth todo", true)
      )
    )
  }
}