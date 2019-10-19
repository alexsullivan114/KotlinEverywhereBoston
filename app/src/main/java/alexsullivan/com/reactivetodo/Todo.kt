package alexsullivan.com.reactivetodo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(@PrimaryKey(autoGenerate = true) val id: Long, val text: String, val isChecked: Boolean)
