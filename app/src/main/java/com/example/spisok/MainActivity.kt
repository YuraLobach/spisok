package com.example.spisok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    var counter = 0
    lateinit var adapter: RecyclerAdapter


    private val list = mutableListOf<Task>()
    private val dbHelper = DBHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list.addAll(dbHelper.getAllTasks())

        adapter = RecyclerAdapter(list) {
            dbHelper.deleteTask(list[it].id)
            list.removeAt(it)
            adapter.notifyItemRemoved(it)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener(){
            val s = editText.text.toString()
            if(s.isNotBlank()) {
                editText.text.clear()
                val id = dbHelper.addTask(s)
                list.add(Task(id, s))
                adapter.notifyItemInserted(list.lastIndex)
            }

        }
    }
}
