package com.example.gestionrecetaschef.ui.registrarpreparacion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarPreparacionPantalla(
    recetaId: Int,
    navController: NavHostController,
    viewModel: RegistrarPreparacionViewModel = viewModel()
) {
    var porciones by remember { mutableStateOf(3) }
    var puntuacion by remember { mutableStateOf(4) }
    var notas by remember { mutableStateOf("") }
    var vecesPreparada by remember { mutableStateOf(1) }

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
                title = { Text("Porciones y puntuación", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.guardarOpinion(
                            recetaId = recetaId,
                            comentario = notas,
                            puntuacion = puntuacion.toDouble(),
                            vecesPreparada = vecesPreparada
                        )
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
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            // 1. Comensales (Porciones)
            item {
                Column {
                    Text("Comensales (porciones)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Selecciona cuántas porciones rinde esta receta.", color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Stepper(
                        value = porciones,
                        onValueChange = { porciones = it },
                        label = "porciones"
                    )
                }
            }

            // 2. Puntuación
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Puntuación de la receta",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        "¿Qué tan buena te ha quedado?",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    RatingBar(rating = puntuacion, onRatingChange = { puntuacion = it })
                    
                    Text(
                        text = puntuacion.toDouble().toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // 3. Notas Personales
            item {
                Column {
                    Text("Notas personales (opcional)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = notas,
                        onValueChange = { notas = it },
                        modifier = Modifier.fillMaxWidth().height(120.dp),
                        placeholder = { Text("Ej. La próxima vez agregar menos sal.") },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF2E7D32),
                            unfocusedBorderColor = Color.LightGray
                        )
                    )
                }
            }

            // 4. Veces Preparada
            item {
                Column {
                    Text("¿Cuántas veces la has preparado?", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text("Lleva el control de cuántas veces has hecho esta receta.", color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Stepper(
                        value = vecesPreparada,
                        onValueChange = { vecesPreparada = it },
                        label = "veces"
                    )
                }
            }

            // 5. Botón Guardar
            item {
                Button(
                    onClick = {
                        viewModel.guardarOpinion(
                            recetaId = recetaId,
                            comentario = notas,
                            puntuacion = puntuacion.toDouble(),
                            vecesPreparada = vecesPreparada
                        )
                    },
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                ) {
                    Text("Guardar receta", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun Stepper(value: Int, onValueChange: (Int) -> Unit, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { if (value > 1) onValueChange(value - 1) }) {
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Menos")
        }
        Text(text = "$value $label", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        IconButton(onClick = { onValueChange(value + 1) }) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Más")
        }
    }
}

@Composable
fun RatingBar(rating: Int, onRatingChange: (Int) -> Unit) {
    Row {
        for (i in 1..5) {
            IconButton(onClick = { onRatingChange(i) }) {
                Icon(
                    imageVector = if (i <= rating) Icons.Default.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = if (i <= rating) Color(0xFFFFB300) else Color.LightGray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
