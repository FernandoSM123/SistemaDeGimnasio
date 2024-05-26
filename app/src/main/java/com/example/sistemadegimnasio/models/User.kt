package com.example.sistemadegimnasio.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val password: String
)
