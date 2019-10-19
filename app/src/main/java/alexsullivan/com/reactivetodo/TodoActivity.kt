package alexsullivan.com.reactivetodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_todo.fab
import kotlinx.android.synthetic.main.activity_todo.root
import kotlinx.android.synthetic.main.activity_todo.todoText

class TodoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_todo)

    fetchDatabase(applicationContext).todoDao().todoObservable()
      .subscribeOn(Schedulers.io())
      .subscribe { print("Woofers") }

    fab.setOnClickListener {
      val todo = Todo(0, todoText.text.toString(), false)
      fetchDatabase(applicationContext).todoDao().insertTask(todo)
        .subscribeOn(Schedulers.io())
        .subscribe { theThing ->
          println(theThing)
          Snackbar.make(root, "Task created", Snackbar.LENGTH_SHORT).show()
        }
    }
  }
}