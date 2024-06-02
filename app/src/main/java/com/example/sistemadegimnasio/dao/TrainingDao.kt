package com.example.sistemadegimnasio.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.sistemadegimnasio.models.Exercise
import com.example.sistemadegimnasio.models.PersonalRecord
import com.example.sistemadegimnasio.models.Training
import java.time.LocalDate

@Dao
interface TrainingDao {

    @Insert
    fun insertTraining(training: Training)

//    @Query("SELECT * FROM Trainings WHERE date IN (:date)")
//    fun getTrainingsByDate(date: LocalDate): List<Training>

//    @Transaction
//    @Query("SELECT * FROM Trainings INNER JOIN Exercises ON Trainings.exercise.id = Exercises.id WHERE Trainings.date = :date")
//    fun getTrainingsByDate(date: LocalDate): List<Training>

    @Query("SELECT * FROM Trainings WHERE date IN (:date) AND userId = :userId")
    fun getTrainingsByDate(date: LocalDate, userId: Int): List<Training>
}