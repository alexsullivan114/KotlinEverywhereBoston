package alexsullivan.com.reactivetodo

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(todo: Todo): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTasks(todos: List<Todo>): List<Long>

    @Query("SELECT * FROM Todo")
    fun todoObservable(): Flowable<List<Todo>>

    @Delete
    suspend fun deleteTodo(todo: Todo)
}