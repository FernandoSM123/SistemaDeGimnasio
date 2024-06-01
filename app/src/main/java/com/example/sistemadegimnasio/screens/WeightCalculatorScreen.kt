package com.example.sistemadegimnasio.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.sistemadegimnasio.ui.theme.Purple40
import com.example.sistemadegimnasio.viewModel.PesoException


import com.example.sistemadegimnasio.viewModel.WeightCalculatorViewModel


@Composable
fun WeightCalculatorScreen(navController: NavController, viewModel: WeightCalculatorViewModel) {
    var pesoDeseado by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = { TopBar(navController, "Calculadora de Peso") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.75f),
                value = pesoDeseado,
                //onValueChange = { pesoDeseado = it },
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        pesoDeseado = newValue
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Peso Deseado:") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.75f),
                onClick = {
                    try {
                        val pesoTotalInt = pesoDeseado.toInt()
                        resultado = viewModel.distribuirPeso(pesoTotalInt)
                    } catch (e: NumberFormatException) {
                        Toast.makeText(
                            context,
                            "Error: El valor ingresado no es un número válido",
                            Toast.LENGTH_LONG
                        ).show()
                        resultado = ""
                    } catch (e: PesoException) {
                        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        resultado = ""
                    }
                }
            ) {
                Text("Calcular")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(resultado)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeightCalculatorScreenPreview() {
    WeightCalculatorScreen(
        navController = rememberNavController(),
        viewModel = WeightCalculatorViewModel()
    )
}
