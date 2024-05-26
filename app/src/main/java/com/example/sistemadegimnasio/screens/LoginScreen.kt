package com.example.sistemadegimnasio.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.example.sistemadegimnasio.viewModel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    var username by remember { mutableStateOf("johnDoe@gmail.com") }
    var password by remember { mutableStateOf("123") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            viewModel.login(username, password) { success ->
                if (success) {
                    navController.navigate("main")
                } else {
                    Toast.makeText(context, "Credenciales Invalidas", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Login")
        }
    }
}