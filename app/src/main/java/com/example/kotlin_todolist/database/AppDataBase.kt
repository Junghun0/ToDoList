package com.example.kotlin_todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun todoDao(): TodoDao
}