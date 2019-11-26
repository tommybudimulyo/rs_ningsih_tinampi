package com.example.rsningsihtinampi.jetpack

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rsningsihtinampi.model.Other

@Dao
interface UserDao {
    @Insert
    fun createData(data: Other)

    @Query("SELECT * FROM other WHERE name = :name")
    fun readData(name: String): LiveData<Other>

    @Update
    fun updateData(other: Other)

    @Delete
    fun deleteData(other: Other)
}