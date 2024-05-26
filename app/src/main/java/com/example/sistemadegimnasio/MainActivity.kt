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
import com.example.sistemadegimnasio.dao.UserDao
import com.example.sistemadegimnasio.models.User
import com.example.sistemadegimnasio.ui.theme.SistemaDeGimnasioTheme

class MainActivity : ComponentActivity() {

    lateinit var DB_connection: AppDatabase
    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DB_connection = DBConnection.buildConnection(this)
        this.userDao = DB_connection.userDao()

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
                    GymApp()
                }
            }
        }
    }

    //GENERAR DATOS DE PRUEBA
    fun initData() {
        val u1 = User(email = "johnDoe@gmail.com", password = "123")
        val u2 = User(email = "janeDoe@gmail.com", password = "123")
        this.userDao.insertUser(u1)
        this.userDao.insertUser(u2)
    }
}