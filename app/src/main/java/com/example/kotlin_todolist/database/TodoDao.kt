package com.example.kotlin_todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao{
    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<List<Todo>>

    @Insert
    fun insert(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}