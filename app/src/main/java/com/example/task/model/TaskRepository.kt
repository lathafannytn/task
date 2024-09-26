package com.example.task.model

import Task
import TaskDao
import TaskStatus
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<Task>> = taskDao.getAllTasks()

    fun getTasksByStatus(status: TaskStatus): Flow<List<Task>> {
        return taskDao.getTasksByStatus(status)
    }

    suspend fun insert(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun delete(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun update(task: Task) {
        taskDao.updateTask(task)
    }
}

