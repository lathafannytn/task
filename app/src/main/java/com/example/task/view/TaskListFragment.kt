package com.example.task.view

import TaskAdapter
import TaskViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task.R

class TaskListFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvStatus)
        taskAdapter = TaskAdapter { task ->
            Toast.makeText(requireContext(), "Clicked: ${task.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel.allTasks.observe(viewLifecycleOwner, { tasks ->
            taskAdapter.submitList(tasks)
        })

        val btnAddTask = view.findViewById<Button>(R.id.btnAddTask)
        btnAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_createTaskFragment)
        }
    }
}
