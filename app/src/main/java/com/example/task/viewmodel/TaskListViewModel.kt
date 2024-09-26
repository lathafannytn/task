package com.example.task.viewmodel


import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class TaskListViewModel(application: Application):AndroidViewModel(application) {

//    val tasksLD = MutableLiveData<List<Task>>()
//    val loadingErrorLD = MutableLiveData<Boolean>()
//    val loadingLD = MutableLiveData<Boolean>()


    private var TAG = "volleyTag"
    private var queue:RequestQueue ?= null

    fun refresh(){
//        val task1 = TaskModel("0", "fanny","iyaaaaaaa", "02/05/2024","20:00","jj.jpg",TaskStatus.PENDING,"1")
//        val task2 = TaskModel("1", "fanny","iyaaaaaaa", "02/05/2024","20:00","jj.jpg",TaskStatus.PENDING,"2")
//
//
//        tasksLD.value = arrayListOf<TaskModel>(task1,task2)
//        loadingErrorLD.value = false
//        loadingLD.value = true
    }

}