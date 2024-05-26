package com.example.sistemadegimnasio.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
)