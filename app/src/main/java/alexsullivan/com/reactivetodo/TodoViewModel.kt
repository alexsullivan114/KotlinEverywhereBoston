package alexsullivan.com.reactivetodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TodoViewModel(service: TodoNetworkService, private val db: TodoDatabase) : ViewModel() {
  private val itemsChannel = ConflatedBroadcastChannel<List<Todo>>()
  val itemsFlow = itemsChannel.asFlow()

  init {
    viewModelScope.launch {
      val networkItems = service.fetchTodos()
      db.todoDao().insertTasks(networkItems)
      db.todoDao().todoFlow().collect(itemsChannel::send)
    }
  }

  fun todoUpdated(todo: Todo) = viewModelScope.launch {
    db.todoDao().insertTask(todo)
  }

  fun todoDeleted(todo: Todo) = viewModelScope.launch {
    db.todoDao().deleteTodo(todo)
  }
}
