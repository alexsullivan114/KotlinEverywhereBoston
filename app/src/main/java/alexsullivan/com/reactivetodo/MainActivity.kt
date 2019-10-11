package alexsullivan.com.reactivetodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.list

class MainActivity : AppCompatActivity() {

  private val adapter = TodoAdapter()
  private val disposables = CompositeDisposable()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    list.layoutManager = LinearLayoutManager(this)
    list.adapter = adapter

    val viewModel = buildViewModel { TodoViewModel() }

    viewModel.itemsObserable
      .subscribe { adapter.items = it }
      .addTo(disposables)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }
}
