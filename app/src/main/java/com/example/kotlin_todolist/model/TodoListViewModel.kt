package com.example.kotlin_todolist.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.example.kotlin_todolist.database.AppDataBase
import com.example.kotlin_todolist.database.Todo

class TodoListViewModel(application: Application) : AndroidViewModel(application){

    private val db : AppDataBase by lazy {
        Room.databaseBuilder(
            application,
            AppDataBase::class.java, "todoList"
        ).allowMainThreadQueries()
            .build()
    }

    fun insert(todo: Todo){
        db.todoDao().insert(todo)
    }

    fun update(todo: Todo){
        db.todoDao().update(todo)
    }

    fun delete(todo: Todo){
        db.todoDao().delete(todo)
    }
}