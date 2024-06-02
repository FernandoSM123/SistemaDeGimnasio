@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.sistemadegimnasio.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api


import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import java.util.*


import com.example.sistemadegimnasio.models.Training
import com.example.sistemadegimnasio.viewModel.TrainingViewModel
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WorkoutsScreen(navController: NavController, viewModel: TrainingViewModel) {
    val trainings by viewModel.trainings.collectAsState(initial = emptyList())
    val date = remember { mutableStateOf(LocalDate.now()) }

    //CUANDO CAMBIA LA FECHA SE LLAMA AL METODO: getTrainings
    LaunchedEffect(date.value) {
        viewModel.getTrainings(date.value)
    }

    // Llamar al método getTrainings cuando se inicia la vista
    LaunchedEffect(Unit) {
        viewModel.getTrainings(date.value)
    }

    Scaffold(
        topBar = { TopBar(navController, "Entrenamientos") },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = TopAppBarHeight)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomDatePicker(
                    value = date.value,
                    onValueChange = { newDate ->
                        date.value = newDate
                        viewModel.getTrainings(newDate)
                    }
                )
                TrainingList(trainings)
            }
        }
    )
}

@Composable
fun TrainingList(trainings: List<Training>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        itemsIndexed(trainings) { index, training ->
            TrainingItem(index + 1, training)
        }
    }
}

@Composable
fun TrainingItem(counter: Int, training: Training) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = "# $counter", style = MaterialTheme.typography.titleMedium)
        Text(text = training.exercise.name, style = MaterialTheme.typography.bodyLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    value: LocalDate,
    onValueChange: (LocalDate) -> Unit
) {

    val open = remember { mutableStateOf(false) }

    if (open.value) {
        CalendarDialog(
            state = rememberUseCaseState(
                visible = true,
                true,
                onCloseRequest = { open.value = false }),
            config = CalendarConfig(
                yearSelection = true,
                style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
                selectedDate = value
            ) { newDate ->
                onValueChange(newDate)
            },
        )
    }

    TextField(
        modifier = Modifier
            .clickable {
                open.value = true
            }
            .fillMaxWidth()
            .padding(top = 10.dp),
        label = { Text("Seleccione una fecha:") },
        enabled = false,
        value = value.format(DateTimeFormatter.ISO_DATE),
        onValueChange = {},
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    )
}