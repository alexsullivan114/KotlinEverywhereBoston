package alexsullivan.com.reactivetodo

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(todo: Todo): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTasks(todos: List<Todo>): Single<List<Long>>

    @Query("SELECT * FROM Todo")
    fun todoObservable(): Flowable<List<Todo>>

    @Delete
    fun deleteTodo(todo: Todo): Completable
}