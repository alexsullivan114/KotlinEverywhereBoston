package alexsullivan.com.reactivetodo

import io.reactivex.rxjava3.core.Single

interface TodoNetworkService {
  fun fetchTodos(): Single<List<Todo>>
}
