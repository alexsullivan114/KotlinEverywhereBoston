package alexsullivan.com.reactivetodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter: RecyclerView.Adapter<TodoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_todo_row, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

    }
}

class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {

}
