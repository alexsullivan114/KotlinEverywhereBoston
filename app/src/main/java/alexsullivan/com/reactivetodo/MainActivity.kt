package alexsullivan.com.reactivetodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.list
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val db = fetchDatabase(applicationContext)
    val viewModel = buildViewModel { TodoViewModel(TodoNetworkServiceImpl(db), db) }
    val adapter = TodoAdapter { viewModel.todoUpdated(it) }


    val callback = DismissHelper {
      val item = adapter.items[it.adapterPosition]
      viewModel.todoDeleted(item)
    }

    ItemTouchHelper(callback).attachToRecyclerView(list)
    list.layoutManager = LinearLayoutManager(this)
    list.adapter = adapter


    lifecycleScope.launch {
      viewModel.itemsFlow
        .collect { adapter.items = it }
    }

    fab.setOnClickListener {
      startActivity(Intent(this, TodoActivity::class.java))
    }
  }
}
