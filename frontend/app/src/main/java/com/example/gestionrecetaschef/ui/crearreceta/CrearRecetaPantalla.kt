package com.example.gestionrecetaschef.ui.crearreceta

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.gestionrecetaschef.domain.modelo.CrearRecetaRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearRecetaPantalla(
    navController: NavHostController,
    viewModel: CrearRecetaViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var tiempoPreparacion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }
    var ingredienteActual by remember { mutableStateOf("") }
    var listaIngredientes by remember { mutableStateOf(listOf<String>()) }
    var pasoActual by remember { mutableStateOf("") }
    var listaPasos by remember { mutableStateOf(listOf<String>()) }

    val guardado by viewModel.guardado.collectAsState()

    LaunchedEffect(guardado) {
        if (guardado) {
            viewModel.resetGuardado()
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva receta", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (nombre.isNotBlank() && categoria.isNotBlank() && tiempoPreparacion.isNotBlank()) {
                            // Ahora enviamos solo lo que el backend nuevo espera
                            val receta = CrearRecetaRequest(
                                nombre = nombre,
                                descripcion = descripcion,
                                categoria = categoria,
                                tiempo_preparacion = tiempoPreparacion.toIntOrNull() ?: 0,
                                imagen = if (imagenUrl.isBlank()) null else imagenUrl,
                                ingredientes = listaIngredientes,
                                pasos = listaPasos
                            )
                            viewModel.crearReceta(receta)
                        }
                    }) {
                        Icon(Icons.Default.Check, contentDescription = "Guardar", tint = Color(0xFF2E7D32))
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF5F5F5)),
                    contentAlignment = Alignment.Center
                ) {
                    if (imagenUrl.isBlank()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = Color.LightGray, modifier = Modifier.size(40.dp))
                            Text("Pega una URL abajo para ver la foto", color = Color.LightGray, fontSize = 14.sp)
                        }
                    } else {
                        AsyncImage(
                            model = imagenUrl,
                            contentDescription = "Vista previa",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            item {
                CustomTextField(value = nombre, onValueChange = { nombre = it }, label = "Nombre de la receta")
            }

            item {
                CustomTextField(value = descripcion, onValueChange = { descripcion = it }, label = "Descripción (opcional)", singleLine = false)
            }

            item {
                CustomTextField(value = categoria, onValueChange = { categoria = it }, label = "Categoría")
            }

            item {
                CustomTextField(
                    value = imagenUrl, 
                    onValueChange = { imagenUrl = it }, 
                    label = "URL de la imagen (Ej: https://...)",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri, imeAction = ImeAction.Next)
                )
            }

            item {
                SectionHeader(title = "Ingredientes", onAddClick = {
                    if (ingredienteActual.isNotBlank()) {
                        listaIngredientes = listaIngredientes + ingredienteActual
                        ingredienteActual = ""
                    }
                })
                OutlinedTextField(
                    value = ingredienteActual,
                    onValueChange = { ingredienteActual = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Escribe un ingrediente...") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF2E7D32))
                )
            }

            itemsIndexed(listaIngredientes) { index, item ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { listaIngredientes = listaIngredientes.toMutableList().apply { removeAt(index) } }) {
                        Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red)
                    }
                    Text(item, fontSize = 16.sp)
                }
            }

            item {
                SectionHeader(title = "Preparación", onAddClick = {
                    if (pasoActual.isNotBlank()) {
                        listaPasos = listaPasos + pasoActual
                        pasoActual = ""
                    }
                })
                OutlinedTextField(
                    value = pasoActual,
                    onValueChange = { pasoActual = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Escribe un paso...") },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF2E7D32))
                )
            }

            itemsIndexed(listaPasos) { index, item ->
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Surface(
                        color = Color(0xFFE8F5E9),
                        shape = CircleShape,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("${index + 1}", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(item, fontSize = 16.sp, modifier = Modifier.weight(1f))
                    IconButton(onClick = { listaPasos = listaPasos.toMutableList().apply { removeAt(index) } }, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.Close, contentDescription = null, tint = Color.Gray)
                    }
                }
            }

            item {
                Text("Tiempo de preparación", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        value = tiempoPreparacion,
                        onValueChange = { tiempoPreparacion = it },
                        modifier = Modifier.width(100.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0xFF2E7D32))
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("minutos", color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String, 
    onValueChange: (String) -> Unit, 
    label: String, 
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column {
        Text(label, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = singleLine,
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = keyboardOptions,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2E7D32),
                unfocusedBorderColor = Color.LightGray
            )
        )
    }
}

@Composable
fun SectionHeader(title: String, onAddClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        TextButton(onClick = onAddClick) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
            Text(" Agregar", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
        }
    }
}
