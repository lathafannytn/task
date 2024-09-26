import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val dateTime: Long,
    val reminder: Long,
    val status: TaskStatus,
    val filePath: String? = null
)

enum class TaskStatus {
    PENDING, IN_PROGRESS, COMPLETED
}
