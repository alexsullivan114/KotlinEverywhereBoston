package alexsullivan.com.reactivetodo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val viewModel = buildViewModel { TodoViewModel(TodoNetworkServiceImpl()) }
    val adapter = TodoAdapter { startActivity(Intent(this, TodoActivity::class.java)) }
    list.layoutManager = LinearLayoutManager(this)
    list.adapter = adapter


    viewModel.itemsObservable
      .subscribe { adapter.items = it }
      .addTo(disposables)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }
}
