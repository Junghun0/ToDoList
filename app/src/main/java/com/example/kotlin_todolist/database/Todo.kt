package com.example.kotlin_todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Todo(var title: String, var date: Long ): Serializable {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}