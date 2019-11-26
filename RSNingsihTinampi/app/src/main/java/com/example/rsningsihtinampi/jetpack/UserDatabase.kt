package com.example.rsningsihtinampi.jetpack

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rsningsihtinampi.model.Other

@Database(entities = [Other::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}