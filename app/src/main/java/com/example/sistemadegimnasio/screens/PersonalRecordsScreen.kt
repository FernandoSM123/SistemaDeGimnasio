package com.example.sistemadegimnasio.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sistemadegimnasio.models.PersonalRecord

import com.example.sistemadegimnasio.viewModel.PersonalRecordsViewModel

val TopAppBarHeight = 56.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PersonalRecordsScreen(navController: NavController, viewModel: PersonalRecordsViewModel) {
    val prs by viewModel.prs.collectAsState(initial = emptyList())
    val selectedRecord by viewModel.selectedRecord.collectAsState()

    Scaffold(
        topBar = { TopBar(navController, "Records Personales") },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = TopAppBarHeight)
            ) {
                fun selectRecord(personalRecord: PersonalRecord) {
                    viewModel.selectRecord(personalRecord)
                }
                PersonalRecordList(prs, ::selectRecord)
                selectedRecord?.let { record ->
                    EditRecordDialog(
                        record = record,
                        onDismiss = { viewModel.selectRecord(null) },
                        onSave = { viewModel.updatePR(it) })
                }
            }
        }
    )
}

@Composable
fun PersonalRecordList(records: List<PersonalRecord>, onRecordClick: (PersonalRecord) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(records) { record ->
            PersonalRecordItem(record, onClick = { onRecordClick(record) })
        }
    }
}

@Composable
fun PersonalRecordItem(record: PersonalRecord, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Text(text = record.exerciseName, style = MaterialTheme.typography.titleLarge)
        Text(text = "Peso Maximo: ${record.maxWeight}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun EditRecordDialog(
    record: PersonalRecord,
    onSave: (PersonalRecord) -> Unit,
    onDismiss: () -> Unit
) {
    var maxWeight by remember { mutableStateOf(record.maxWeight) }
    var exerciseName by remember { mutableStateOf(record.exerciseName) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Peso Maximo", textAlign = TextAlign.Center) },
        text = {
            Column {
                TextField(
                    enabled = false,
                    value = exerciseName,
                    onValueChange = { exerciseName = it },
                    label = { Text("Nombre del Ejercicio:") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = maxWeight.toString(),
                    onValueChange = { maxWeight = it.toIntOrNull() ?: maxWeight },
                    label = { Text("Peso Maximo:") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(record.copy(maxWeight = maxWeight, exerciseName = exerciseName))
            }) {
                Text("Actualizar")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
            ) {
                Text("Cancelar")
            }
        }
    )
}