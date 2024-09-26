import androidx.lifecycle.*
import com.example.task.model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    // Semua task dalam bentuk LiveData
    val allTasks: LiveData<List<Task>> = repository.allTasks.asLiveData()

    // Task yang Pending
    val pendingTasks: LiveData<List<Task>> = repository.getTasksByStatus(TaskStatus.PENDING).asLiveData()

    // Task yang In Progress
    val inProgressTasks: LiveData<List<Task>> = repository.getTasksByStatus(TaskStatus.IN_PROGRESS).asLiveData()

    // Task yang Completed
    val completedTasks: LiveData<List<Task>> = repository.getTasksByStatus(TaskStatus.COMPLETED).asLiveData()

    // Fungsi untuk menambahkan task baru
    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    // Fungsi untuk mendapatkan task berdasarkan status tertentu
    fun getTasksByStatus(status: TaskStatus): LiveData<List<Task>> {
        return repository.getTasksByStatus(status).asLiveData()
    }

    // Fungsi untuk menghapus task
    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

    // Fungsi untuk mengupdate task
    fun updateTask(task: Task) = viewModelScope.launch {
        repository.update(task)
    }
}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
