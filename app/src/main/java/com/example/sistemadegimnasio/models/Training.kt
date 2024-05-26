package com.example.sistemadegimnasio.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "Trainings")
data class Training(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val exerciseId: Int,
    val date: LocalDate
)