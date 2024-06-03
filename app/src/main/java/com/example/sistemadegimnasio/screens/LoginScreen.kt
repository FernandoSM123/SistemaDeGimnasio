package com.example.sistemadegimnasio.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sistemadegimnasio.viewModel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    var email by remember { mutableStateOf("johnDoe@gmail.com") }
    var password by remember { mutableStateOf("123") }
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeaderImage(modifier = Modifier)

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "Sistema de Gimnasio",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.padding(10.dp))

        //EMAIL
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        Spacer(modifier = Modifier.padding(10.dp))

        //CONTRASENA
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.padding(10.dp))

        //LOGIN BUTTON
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                viewModel.login(email, password) { success ->
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

@Composable
fun HeaderImage(modifier: Modifier) {
    val painter = painterResource(id = com.example.sistemadegimnasio.R.drawable.login2)
    Image(
        painter = painter,
        contentDescription = "Header",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(64.dp)
            .height(64.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val context = LocalContext.current
    LoginScreen(
        navController = rememberNavController(),
        viewModel = LoginViewModel(context)
    )
}

