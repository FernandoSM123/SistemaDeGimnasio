package com.example.sistemadegimnasio.models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "PersonalRecords")
data class PersonalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val exerciseId: Int,
    val maxWeight: Int,
    @Ignore val exerciseName: String = ""
) {
    // Constructor sin @Ignore para Room
    constructor(id: Int, userId: Int, exerciseId: Int, maxWeight: Int) : this(id, userId, exerciseId, maxWeight, "")
}