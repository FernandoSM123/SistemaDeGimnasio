package com.example.sistemadegimnasio.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sistemadegimnasio.models.Exercise
import com.example.sistemadegimnasio.models.PersonalRecord
import com.example.sistemadegimnasio.models.Training
import com.example.sistemadegimnasio.models.User
import com.example.sistemadegimnasio.models.Converters


@Database(
    entities = [User::class, Exercise::class, PersonalRecord::class, Training::class],
    version = 5
)
@TypeConverters(*[Converters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun personalRecordDao(): PersonalRecordDao
    abstract fun trainingDao(): TrainingDao
}