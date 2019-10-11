package alexsullivan.com.reactivetodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.list

class MainActivity : AppCompatActivity() {

  private val adapter = TodoAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    list.layoutManager = LinearLayoutManager(this)
    list.adapter = adapter

    adapter.items = listOf(
      Todo("First todo", false),
      Todo("Second todo", false),
      Todo("Third todo", true),
      Todo("Fourth todo", false),
      Todo("Fifth todo", true)
    )
  }
}
