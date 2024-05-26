package com.example.sistemadegimnasio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.sistemadegimnasio.Screen
import com.example.sistemadegimnasio.viewModel.LoginViewModel


@Composable
fun MainScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Button(onClick = { navController.navigate(Screen.Workouts.route) }) {
            Text("Entrenamientos")
        }
        Button(onClick = { navController.navigate(Screen.PersonalRecords.route) }) {
            Text("Records Personales")
        }
        Button(onClick = { navController.navigate(Screen.WeightCalculator.route) }) {
            Text("Calculadora de Pesos")
        }
        Button(onClick = { navController.navigate(Screen.Login.route) }) {
            Text("Salir")
        }
    }
}
