package com.example.sistemadegimnasio

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sistemadegimnasio.dao.AppDatabase

import com.example.sistemadegimnasio.screens.LoginScreen
import com.example.sistemadegimnasio.screens.MainScreen
import com.example.sistemadegimnasio.screens.PersonalRecordsScreen
import com.example.sistemadegimnasio.screens.WeightCalculatorScreen
import com.example.sistemadegimnasio.screens.WorkoutsScreen
import com.example.sistemadegimnasio.viewModel.LoginViewModel
import com.example.sistemadegimnasio.viewModel.PersonalRecordsViewModel
import com.example.sistemadegimnasio.viewModel.WeightCalculatorViewModel
import com.example.sistemadegimnasio.viewModel.WorkoutViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Main : Screen("main")
    object Workouts : Screen("workouts")
    object PersonalRecords : Screen("personalRecords")
    object WeightCalculator : Screen("weightCalculator")
}


@Composable
fun GymApp(startDestination: String, context: Context) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = startDestination) {

        //LOGIN
        composable(Screen.Login.route) { LoginScreen(navController, LoginViewModel(context)) }

        //VENTANA PRINCIPAL
        composable(Screen.Main.route) { MainScreen(navController) }

        //ENTRENAMIENTOS
        composable(Screen.Workouts.route) { WorkoutsScreen(navController, WorkoutViewModel()) }

        //RECORDS PERSONALES
        composable(Screen.PersonalRecords.route) {
            PersonalRecordsScreen(
                navController,
                PersonalRecordsViewModel(context)
            )
        }

        //CALCULAR PESOS
        composable(Screen.WeightCalculator.route) {
            WeightCalculatorScreen(
                navController,
                WeightCalculatorViewModel()
            )
        }
    }
}