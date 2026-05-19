package com.example.gestionrecetaschef.ui.crearreceta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import com.example.gestionrecetaschef.domain.modelo.CrearRecetaRequest

@Composable
fun CrearRecetaPantalla(

    navController: NavHostController,

    viewModel: CrearRecetaViewModel =
        viewModel()

) {

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var tiempoPreparacion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }
    var ingrediente by remember { mutableStateOf("") }
    var listaIngredientes by remember { mutableStateOf(listOf<String>()) }
    var paso by remember { mutableStateOf("") }
    var listaPasos by remember { mutableStateOf(listOf<String>()) }
    var comentario by remember { mutableStateOf("") }
    var puntuacion by remember { mutableStateOf("") }
    var vecesPreparada by remember { mutableStateOf("") }

    val guardado by viewModel.guardado.collectAsState()

    LaunchedEffect(guardado) {
        if (guardado) {
            viewModel.resetGuardado()
            navController.popBackStack()
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        item {
            Text(
                text = "Crear receta",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        item {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nombre") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
        }

        item {
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Descripción") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
        }

        item {
            OutlinedTextField(
                value = categoria,
                onValueChange = { categoria = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Categoría") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
        }

        item {
            OutlinedTextField(
                value = imagenUrl,
                onValueChange = { imagenUrl = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("URL de imagen") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Next
                )
            )
        }

        item {
            OutlinedTextField(
                value = ingrediente,
                onValueChange = { ingrediente = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Ingrediente") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
        }

        item {
            Button(onClick = {
                if (ingrediente.isNotBlank()) {
                    listaIngredientes = listaIngredientes + ingrediente
                    ingrediente = ""
                }
            }) {
                Text("Agregar ingrediente")
            }
        }

        items(listaIngredientes.size) { index ->
            Text("• ${listaIngredientes[index]}")
        }

        item {
            OutlinedTextField(
                value = paso,
                onValueChange = { paso = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Paso") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
        }

        item {
            Button(onClick = {
                if (paso.isNotBlank()) {
                    listaPasos = listaPasos + paso
                    paso = ""
                }
            }) {
                Text("Agregar paso")
            }
        }

        items(listaPasos.size) { index ->
            Text("${index + 1}. ${listaPasos[index]}")
        }

        item {
            OutlinedTextField(
                value = comentario,
                onValueChange = { comentario = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Comentario inicial") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
        }

        item {
            OutlinedTextField(
                value = puntuacion,
                onValueChange = { puntuacion = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Puntuación") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                )
            )
        }

        item {
            OutlinedTextField(
                value = vecesPreparada,
                onValueChange = { vecesPreparada = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Veces preparada") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
        }

        item {
            OutlinedTextField(
                value = tiempoPreparacion,
                onValueChange = { tiempoPreparacion = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Tiempo preparación (min)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (
                        nombre.isNotBlank() &&
                        descripcion.isNotBlank() &&
                        categoria.isNotBlank() &&
                        tiempoPreparacion.isNotBlank()
                    ) {
                        val receta = CrearRecetaRequest(
                            nombre = nombre,
                            descripcion = descripcion,
                            categoria = categoria,
                            tiempo_preparacion = tiempoPreparacion.toInt(),
                            imagen = imagenUrl,
                            ingredientes = listaIngredientes,
                            pasos = listaPasos,
                            comentario = comentario,
                            puntuacion = puntuacion.toDoubleOrNull() ?: 0.0,
                            veces_preparada = vecesPreparada.toIntOrNull() ?: 0
                        )
                        viewModel.crearReceta(receta)
                    }
                }
            ) {
                Text(text = "Guardar receta")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
