package com.example.sistemadegimnasio.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate


//@Entity(tableName = "Trainings")
//data class Training(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val userId: Int,
//    val exerciseId: Int,
//    val date: LocalDate,
//    @Ignore val exercise: Exercise? = null
//){
//    // Constructor sin @Ignore para Room
//    constructor(id: Int, userId: Int, exerciseId: Int, date: LocalDate) : this(id, userId, exerciseId, date, null)
//}

@Entity(tableName = "Trainings")
data class Training(
    @PrimaryKey(autoGenerate = true) val trainingId: Int = 0,
    val userId: Int,
    //val exerciseId: Int,
    val date: LocalDate,
    @Embedded val exercise: Exercise
)