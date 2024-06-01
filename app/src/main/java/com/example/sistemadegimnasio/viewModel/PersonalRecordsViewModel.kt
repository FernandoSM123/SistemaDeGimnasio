package com.example.sistemadegimnasio.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemadegimnasio.dao.DBConnection
import com.example.sistemadegimnasio.models.PersonalRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonalRecordsViewModel(private val context: Context) : ViewModel() {

    private var userID: Int
    private val prDao = DBConnection.getConnection().personalRecordDao()
    private val exerciseDao = DBConnection.getConnection().exerciseDao()

    private val _prs = MutableStateFlow<List<PersonalRecord>>(emptyList())
    val prs: StateFlow<List<PersonalRecord>> = _prs

    private val _selectedRecord = MutableStateFlow<PersonalRecord?>(null)
    val selectedRecord: StateFlow<PersonalRecord?> = _selectedRecord

    val exercisesOfInterest = listOf("Bench Press", "Shoulder Press", "Snatch", "Deadlift")

    fun selectRecord(record: PersonalRecord?) {
        _selectedRecord.value = record
    }

    init {
        //obtener userId  de sharedPreferences
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("GymApp", Context.MODE_PRIVATE)
        userID = sharedPreferences.getInt("userID", 0)

        //SI NO HAY RECORDS PERSONALES DEL USUARIO (GENERARLOS)
        if (prDao.getPersonalRecordsByUser_Count(userID) == 0) {
            initPRs()
        }

        viewModelScope.launch {
            _prs.value = prDao.getPersonalRecordsWithExerciseName(userID)
        }
    }

    //ACTUALIZAR RECORD PERSONAL
    fun updatePR(pr: PersonalRecord) {
        viewModelScope.launch {
            prDao.updatePersonalRecord(pr)
            _prs.value = prDao.getPersonalRecordsWithExerciseName(userID)
            _selectedRecord.value = null
            Toast.makeText(
                context,
                "Peso Maximo Actualizado",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    //INICIALIZAR RECORDS PERSONALES DE USUARIO (SI NO EXISTEN)
    //CON LOS EJERCICIOS DE INTERES
    fun initPRs() {
        viewModelScope.launch {
            val exercises = exerciseDao.getAllExercises()
            if (exercises != null) {
                for (exercise in exercises) {
                    val exerciseName = exercise?.name ?: continue
                    if (exerciseName in exercisesOfInterest) {
                        val exerciseId = exercise.id
                        val userPr =
                            PersonalRecord(userId = userID, exerciseId = exerciseId, maxWeight = 0)
                        prDao.insertPersonalRecord(userPr)
                    }
                }
            }
        }
    }
}