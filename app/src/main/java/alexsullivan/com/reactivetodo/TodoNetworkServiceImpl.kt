package alexsullivan.com.reactivetodo

import io.reactivex.rxjava3.core.Single

class TodoNetworkServiceImpl : TodoNetworkService {
  override fun fetchTodos(): Single<List<Todo>> {
    return Single.just(
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