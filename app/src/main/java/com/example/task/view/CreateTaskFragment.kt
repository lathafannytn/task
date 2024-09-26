package com.example.task.view

import Task
import TaskViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task.R
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskTitleEditText: EditText
    private lateinit var taskDescriptionEditText: EditText
    private lateinit var taskDateEditText: EditText
    private lateinit var taskTimeEditText: EditText
    private lateinit var taskStatusSpinner: Spinner
    private lateinit var uploadImageButton: Button
    private lateinit var taskImageNameEditText: EditText
    private lateinit var addTaskButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Menghubungkan View dari XML
        taskTitleEditText = view.findViewById(R.id.addTaskTitle)
        taskDescriptionEditText = view.findViewById(R.id.addTaskDescription)
        taskDateEditText = view.findViewById(R.id.taskDate)
        taskTimeEditText = view.findViewById(R.id.txtTime)
        taskStatusSpinner = view.findViewById(R.id.statusSpinner)
        uploadImageButton = view.findViewById(R.id.uploadImageButton)
        taskImageNameEditText = view.findViewById(R.id.taskImageName)
        addTaskButton = view.findViewById(R.id.addTask)

        // Setup Spinner untuk Task Status
        val statusAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            TaskStatus.values().map { it.name }
        )
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        taskStatusSpinner.adapter = statusAdapter

        // Action ketika Button "Select Image" ditekan (Contoh placeholder, sesuaikan dengan cara upload file Anda)
        uploadImageButton.setOnClickListener {
            // Contoh placeholder untuk menghandle pemilihan gambar
            taskImageNameEditText.setText("example_image.png")
        }

        // Action ketika Button "Add Task" ditekan
        addTaskButton.setOnClickListener {
            val title = taskTitleEditText.text.toString()
            val description = taskDescriptionEditText.text.toString()
            val dateTime = parseDateTime(taskDateEditText.text.toString(), taskTimeEditText.text.toString())
            val status = TaskStatus.valueOf(taskStatusSpinner.selectedItem.toString())
            val filePath = taskImageNameEditText.text.toString()

            // Validasi input
            if (title.isBlank() || description.isBlank() || dateTime == 0L) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Buat objek Task baru
            val newTask = Task(
                name = title,
                description = description,
                dateTime = dateTime,
                reminder = dateTime,  // Sesuaikan jika reminder berbeda
                status = status,
                filePath = filePath
            )

            // Simpan task baru melalui ViewModel
            taskViewModel.insertTask(newTask)

            // Kembali ke halaman sebelumnya
            findNavController().navigateUp()
        }
    }

    // Fungsi untuk mengonversi tanggal dan waktu ke dalam format timestamp (long)
    private fun parseDateTime(date: String, time: String): Long {
        return try {
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val dateTimeString = "$date $time"
            formatter.parse(dateTimeString)?.time ?: 0L
        } catch (e: Exception) {
            0L
        }
    }
}
