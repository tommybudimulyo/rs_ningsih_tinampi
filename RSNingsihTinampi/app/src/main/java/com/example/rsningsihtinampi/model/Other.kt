package com.example.rsningsihtinampi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "other")
data class Other(
    @PrimaryKey
    var name: String,

    @ColumnInfo(name = "value")
    var value: String
)