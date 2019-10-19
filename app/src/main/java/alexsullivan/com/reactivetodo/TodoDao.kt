package alexsullivan.com.reactivetodo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}