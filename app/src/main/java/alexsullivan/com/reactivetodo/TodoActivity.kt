package alexsullivan.com.reactivetodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.coroutines.launch

class TodoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_todo)

    fab.setOnClickListener {
      lifecycleScope.launch {
        val todo = Todo(0, todoText.text.toString(), false)
        fetchDatabase(applicationContext).todoDao().insertTask(todo)
        Snackbar.make(root, "Task created", Snackbar.LENGTH_SHORT).show()
      }
    }
  }
}