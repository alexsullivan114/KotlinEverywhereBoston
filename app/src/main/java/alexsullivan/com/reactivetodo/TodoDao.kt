package alexsullivan.com.reactivetodo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(todo: Todo): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTasks(todos: List<Todo>): List<Long>

    @Query("SELECT * FROM Todo")
    fun todoFlow(): Flow<List<Todo>>

    @Delete
    suspend fun deleteTodo(todo: Todo)
}