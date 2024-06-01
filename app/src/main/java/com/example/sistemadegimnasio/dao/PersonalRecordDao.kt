package com.example.sistemadegimnasio.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.sistemadegimnasio.models.PersonalRecord

@Dao
interface PersonalRecordDao {

    @Insert
    fun insertPersonalRecord(personalRecord: PersonalRecord)

    @Update
    fun updatePersonalRecord(vararg personalRecord: PersonalRecord)

    @Query("SELECT * FROM PersonalRecords WHERE userId IN (:userId)")
    fun getPersonalRecords(userId: Int): List<PersonalRecord?>?

    @Query("SELECT COUNT(*) FROM PersonalRecords WHERE userId IN (:userId)")
    fun getPersonalRecordsByUser_Count(userId: Int): Int

    @Query("""
        SELECT pr.*, e.name AS exerciseName
        FROM PersonalRecords pr
        JOIN Exercises e ON pr.exerciseId = e.id
        WHERE pr.userId = :userId
    """)
    fun getPersonalRecordsByUser(userId: Int): List<PersonalRecord>

    @Transaction
    suspend fun getPersonalRecordsWithExerciseName(userId: Int): List<PersonalRecord> {
        val records = getPersonalRecordsByUser(userId)
        return records.map { record ->
            record.copy(exerciseName = getExerciseNameById(record.exerciseId))
        }
    }

    @Query("SELECT name FROM Exercises WHERE id = :exerciseId")
    suspend fun getExerciseNameById(exerciseId: Int): String
}