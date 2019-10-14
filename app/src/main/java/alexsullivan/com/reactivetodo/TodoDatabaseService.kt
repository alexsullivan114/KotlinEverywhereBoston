package alexsullivan.com.reactivetodo

import io.reactivex.rxjava3.core.Maybe

interface TodoDatabaseService {
  fun loadTodo(id: String): Maybe<Todo>
}