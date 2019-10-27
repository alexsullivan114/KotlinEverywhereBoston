package alexsullivan.com.reactivetodo

interface TodoNetworkService {
  suspend fun fetchTodos(): List<Todo>
}
