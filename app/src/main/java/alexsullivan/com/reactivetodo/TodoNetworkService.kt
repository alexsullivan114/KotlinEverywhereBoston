package alexsullivan.com.reactivetodo

import io.reactivex.Single

interface TodoNetworkService {
  fun fetchTodos(): Single<List<Todo>>
}
