package alexsullivan.com.reactivetodo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

  abstract fun todoDao(): TodoDao
}

var db: TodoDatabase? = null

fun fetchDatabase(context: Context): TodoDatabase {
  if (db == null) {
    db = Room.databaseBuilder(
      context.applicationContext,
      TodoDatabase::class.java,
      "TodoDatabase"
    ).build()
  }

  return db!!
}
