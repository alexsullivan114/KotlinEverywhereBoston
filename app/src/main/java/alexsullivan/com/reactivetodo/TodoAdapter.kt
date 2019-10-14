package alexsullivan.com.reactivetodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_todo_row.view.*

class TodoAdapter(private val toggleListener: (Todo) -> Unit) :
    RecyclerView.Adapter<TodoViewHolder>() {
    var items: List<Todo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_todo_row, parent, false)
        return TodoViewHolder(view, toggleListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = items[position]
        holder.bindItem(item)
    }
}

class TodoViewHolder(view: View, private val toggleListener: (Todo) -> Unit) :
    RecyclerView.ViewHolder(view) {
    fun bindItem(item: Todo) {
        itemView.todoText.text = item.text
        itemView.todoToggle.isChecked = item.isChecked
        itemView.todoToggle.setOnCheckedChangeListener { _, isChecked ->
            toggleListener(
                item.copy(
                    isChecked = isChecked
                )
            )
        }
    }
}
