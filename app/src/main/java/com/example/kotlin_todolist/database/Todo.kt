package com.example.kotlin_todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val date: Long
)