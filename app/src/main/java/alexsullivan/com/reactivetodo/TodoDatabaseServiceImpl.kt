package alexsullivan.com.reactivetodo

import io.reactivex.rxjava3.core.Maybe

class TodoDatabaseServiceImpl: TodoDatabaseService {
  override fun loadTodo(id: String): Maybe<Todo> {
    return Maybe.empty()
  }
}