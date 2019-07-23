package com.example.kotlin_todolist.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.kotlin_todolist.database.AppDataBase
import com.example.kotlin_todolist.database.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListViewModel(application: Application) : AndroidViewModel(application){

    private val db : AppDataBase by lazy {
        Room.databaseBuilder(
            application,
            AppDataBase::class.java, "todoList"
        ).build()
    }

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    fun insert(todo: Todo){
        //background -> dispatchers.io
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().insert(todo)
        }
    }

    fun update(todo: Todo){
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().update(todo)
        }
    }

    fun delete(todo: Todo){
        CoroutineScope(Dispatchers.IO).launch {
            db.todoDao().delete(todo)
        }
    }
}