package alexsullivan.com.reactivetodo

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(todo: Todo): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTasks(todos: List<Todo>): Single<List<Long>>

    @Query("SELECT * FROM Todo")
    fun todoObservable(): Flowable<List<Todo>>

    @Delete
    suspend fun deleteTodo(todo: Todo)
}