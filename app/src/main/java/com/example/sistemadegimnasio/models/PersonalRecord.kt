package com.example.sistemadegimnasio.models
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PersonaRecords")

data class PersonalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val exerciseId: Int,
    val maxWeight: Int
)