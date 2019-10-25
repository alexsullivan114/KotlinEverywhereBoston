package alexsullivan.com.reactivetodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val db = fetchDatabase(applicationContext)
    val viewModel = buildViewModel { TodoViewModel(TodoNetworkServiceImpl(db), db) }
    val adapter = TodoAdapter { viewModel.todoUpdated(it) }


    val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
      override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
      ): Boolean {
        return false
      }

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val item = adapter.items[viewHolder.adapterPosition]
        viewModel.todoDeleted(item)
      }
    }

    ItemTouchHelper(callback).attachToRecyclerView(list)
    list.layoutManager = LinearLayoutManager(this)
    list.adapter = adapter


    viewModel.itemsObservable
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { adapter.items = it }
      .addTo(disposables)

    fab.setOnClickListener {
      startActivity(Intent(this, TodoActivity::class.java))
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }
}
