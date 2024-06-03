package com.example.sistemadegimnasio.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sistemadegimnasio.Screen
import com.example.sistemadegimnasio.viewModel.LoginViewModel
import com.example.sistemadegimnasio.viewModel.WeightCalculatorViewModel


@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            onClick = { navController.navigate(Screen.Workouts.route) }) {
            Text("Entrenamientos")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            onClick = { navController.navigate(Screen.PersonalRecords.route) }) {
            Text("Records Personales")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            onClick = { navController.navigate(Screen.WeightCalculator.route) }) {
            Text("Calculadora de Pesos")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            onClick = { navController.navigate(Screen.Login.route) }) {
            Text("Salir")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        navController = rememberNavController()
    )
}
