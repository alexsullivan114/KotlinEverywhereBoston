package alexsullivan.com.reactivetodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.fab
import kotlinx.android.synthetic.main.activity_main.list

class MainActivity : AppCompatActivity() {

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val db = fetchDatabase(applicationContext)
    val viewModel = buildViewModel { TodoViewModel(TodoNetworkServiceImpl(db), db) }
    val adapter = TodoAdapter { viewModel.todoUpdated(it) }
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
