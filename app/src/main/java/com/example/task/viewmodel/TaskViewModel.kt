import androidx.lifecycle.*
import com.example.task.model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    val allTasks: LiveData<List<Task>> = repository.allTasks.asLiveData()

    val pendingTasks: LiveData<List<Task>> = repository.getTasksByStatus(TaskStatus.PENDING).asLiveData()

    val inProgressTasks: LiveData<List<Task>> = repository.getTasksByStatus(TaskStatus.IN_PROGRESS).asLiveData()

    val completedTasks: LiveData<List<Task>> = repository.getTasksByStatus(TaskStatus.COMPLETED).asLiveData()

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun getTasksByStatus(status: TaskStatus): LiveData<List<Task>> {
        return repository.getTasksByStatus(status).asLiveData()
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

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
