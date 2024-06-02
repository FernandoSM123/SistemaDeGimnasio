package com.example.sistemadegimnasio.viewModel

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemadegimnasio.dao.DBConnection
import com.example.sistemadegimnasio.models.Training
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class TrainingViewModel(private val context: Context) : ViewModel() {

    private var userID: Int = 0
    private val trainingDao = DBConnection.getConnection().trainingDao()
    private val exerciseDao = DBConnection.getConnection().exerciseDao()

    private val _trainings = MutableStateFlow<List<Training>>(emptyList())
    val trainings: StateFlow<List<Training>> = _trainings

    init {
        //obtener userId  de sharedPreferences
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("GymApp", Context.MODE_PRIVATE)
        userID = sharedPreferences.getInt("userID", 0)
    }

    //OBTENER ENTRENAMIENTOS CON BASE A UNA FECHA
    fun getTrainings(date: LocalDate) {
        viewModelScope.launch {
            _trainings.value = trainingDao.getTrainingsByDate(date, userID)

            if (_trainings.value.isEmpty()) {
                initTrainings(date)
                _trainings.value = trainingDao.getTrainingsByDate(date, userID)
            }
        }
    }

    //ESTABLECER ENTRENAMIENTOS PARA USUARIOS EN UNA FECHA
    //CON BASE A UNA SERIE DE EJERCICIOS
    fun initTrainings(date: LocalDate) {
        viewModelScope.launch {
            val exercises = exerciseDao.getAllExercises()
            if (exercises != null) {
                // Mezclar la lista de ejercicios y tomar los primeros 20
                val selectedExercises = exercises.shuffled().take(20)
                for (exercise in selectedExercises) {
                    val exerciseName = exercise?.name ?: continue
                    val exerciseId = exercise.id
                    val training = Training(userId = userID, date = date, exercise = exercise)
                    trainingDao.insertTraining(training)
                }
            }
        }
    }
}