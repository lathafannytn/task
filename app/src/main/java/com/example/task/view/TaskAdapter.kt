import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(private val onTaskClicked: (Task) -> Unit) :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_list_item, parent, false)
        return TaskViewHolder(view, onTaskClicked)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
    }

    class TaskViewHolder(itemView: View, private val onTaskClicked: (Task) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val taskNameTextView: TextView = itemView.findViewById(R.id.txtName)
        private val taskDescriptionTextView: TextView = itemView.findViewById(R.id.txtDesc)
        private val taskTimeTextView: TextView = itemView.findViewById(R.id.txtTime)
        private val taskStatusTextView: TextView = itemView.findViewById(R.id.taskStatus)

        fun bind(task: Task) {
            taskNameTextView.text = task.name
            taskDescriptionTextView.text = task.description
            taskTimeTextView.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(task.dateTime)
            taskStatusTextView.text = task.status.name

            itemView.setOnClickListener {
                onTaskClicked(task)
            }
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}
