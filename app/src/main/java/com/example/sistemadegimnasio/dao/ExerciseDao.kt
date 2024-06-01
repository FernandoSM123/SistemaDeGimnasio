package com.example.sistemadegimnasio.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sistemadegimnasio.models.Exercise

@Dao
interface ExerciseDao {

    @Insert
    fun insertExercise(exercise: Exercise)

    @Query("SELECT * FROM Exercises")
    fun getAllExercises(): List<Exercise?>?
}