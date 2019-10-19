package alexsullivan.com.reactivetodo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface TodoDao {
    @Insert
    fun insertTask(todo: Todo): Single<Long>

    @Query("SELECT * FROM Todo")
    fun todoObservable(): Observable<List<Todo>>
}