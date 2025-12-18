package com.example.myapplication

import android.database.Cursor
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var adapter: SimpleCursorAdapter
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        username = intent.getStringExtra("username")

        val editTextTask = findViewById<EditText>(R.id.edit_text_task)
        val buttonAddTask = findViewById<android.widget.Button>(R.id.button_add_task)
        val listViewTasks = findViewById<ListView>(R.id.list_view_tasks)

        adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_1,
            null,
            arrayOf("task"),
            intArrayOf(android.R.id.text1),
            0
        )
        listViewTasks.adapter = adapter

        loadTasks()

        buttonAddTask.setOnClickListener {
            val task = editTextTask.text.toString()
            if (task.isNotEmpty()) {
                username?.let { user ->
                    if (dbHelper.insertTask(task, user)) {
                        Toast.makeText(this, "Thêm công việc thành công", Toast.LENGTH_SHORT).show()
                        editTextTask.setText("")
                        loadTasks()
                    } else {
                        Toast.makeText(this, "Thêm công việc thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập công việc", Toast.LENGTH_SHORT).show()
            }
        }

        listViewTasks.setOnItemLongClickListener { _, _, _, id ->
            val taskId = id.toString()
            AlertDialog.Builder(this)
                .setTitle("Tùy chọn")
                .setItems(arrayOf("Sửa", "Xóa")) { _, which ->
                    when (which) {
                        0 -> editTask(taskId) // Sửa
                        1 -> deleteTask(taskId) // Xóa
                    }
                }.show()
            true
        }
    }

    private fun loadTasks() {
        username?.let { user ->
            val cursor: Cursor? = dbHelper.getTasks(user)
            adapter.changeCursor(cursor)
        }
    }

    private fun editTask(taskId: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sửa công việc")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("Lưu") { _, _ ->
            val updatedTask = input.text.toString()
            if (updatedTask.isNotEmpty()) {
                if (dbHelper.updateTask(taskId, updatedTask)) {
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                    loadTasks()
                } else {
                    Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Công việc không được để trống", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Hủy") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun deleteTask(taskId: String) {
        AlertDialog.Builder(this)
            .setTitle("Xác nhận xóa")
            .setMessage("Bạn có chắc chắn muốn xóa công việc này?")
            .setPositiveButton("Xóa") { _, _ ->
                if (dbHelper.deleteTask(taskId)) {
                    Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show()
                    loadTasks()
                } else {
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }
}
