package com.example.sistemadegimnasio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room.databaseBuilder
import com.example.sistemadegimnasio.dao.AppDatabase
import com.example.sistemadegimnasio.dao.DBConnection
import com.example.sistemadegimnasio.dao.ExerciseDao
import com.example.sistemadegimnasio.dao.UserDao
import com.example.sistemadegimnasio.models.Exercise
import com.example.sistemadegimnasio.models.User
import com.example.sistemadegimnasio.ui.theme.SistemaDeGimnasioTheme

class MainActivity : ComponentActivity() {

    lateinit var DB_connection: AppDatabase
    lateinit var userDao: UserDao
    lateinit var exerciseDao: ExerciseDao

    val exercises = listOf(
        Exercise(name = "Bench Press"),
        Exercise(name = "Shoulder Press"),
        Exercise(name = "Snatch"),
        Exercise(name = "Clean"),
        Exercise(name = "Deadlift"),
        Exercise(name = "Squat"),
        Exercise(name = "Pull-Up"),
        Exercise(name = "Push-Up"),
        Exercise(name = "Lunge"),
        Exercise(name = "Bent-Over Row"),
        Exercise(name = "Chest Fly"),
        Exercise(name = "Tricep Dip"),
        Exercise(name = "Bicep Curl"),
        Exercise(name = "Leg Press"),
        Exercise(name = "Calf Raise"),
        Exercise(name = "Plank"),
        Exercise(name = "Russian Twist"),
        Exercise(name = "Hanging Leg Raise"),
        Exercise(name = "Cable Crossover"),
        Exercise(name = "Romanian Deadlift"),
        Exercise(name = "Lat Pulldown"),
        Exercise(name = "Incline Bench Press"),
        Exercise(name = "Seated Row"),
        Exercise(name = "Face Pull"),
        Exercise(name = "Hip Thrust"),
        Exercise(name = "Mountain Climber"),
        Exercise(name = "Bulgarian Split Squat"),
        Exercise(name = "Leg Curl"),
        Exercise(name = "Farmer's Walk"),
        Exercise(name = "Hammer Curl")
    )

    val users = listOf(
        User(email = "johnDoe@gmail.com", password = "123"),
        User(email = "janeDoe@gmail.com", password = "123"),
        User(email = "aliceSmith@gmail.com", password = "123"),
        User(email = "bobBrown@gmail.com", password = "123"),
        User(email = "charlieDavis@gmail.com", password = "123"),
        User(email = "dianaEvans@gmail.com", password = "123"),
        User(email = "edwardFrank@gmail.com", password = "123"),
        User(email = "fionaGreen@gmail.com", password = "123"),
        User(email = "georgeHill@gmail.com", password = "123"),
        User(email = "hannahIvy@gmail.com", password = "123")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DB_connection = DBConnection.buildConnection(this)
        this.userDao = DB_connection.userDao()
        this.exerciseDao = DB_connection.exerciseDao()

        if (this.userDao.getUsersCount() == 0) {
            this.initData()
        }
        setContent {
            SistemaDeGimnasioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //GymApp(DB_connection)
                    GymApp(Screen.Login.route, applicationContext)
                }
            }
        }
    }

    //GENERAR DATOS DE PRUEBA
    fun initData() {
        for (user in users) {
            this.userDao.insertUser(user)
        }

        for (exercise in exercises) {
            this.exerciseDao.insertExercise(exercise)
        }
    }
}