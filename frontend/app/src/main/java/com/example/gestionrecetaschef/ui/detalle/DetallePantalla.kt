package com.example.gestionrecetaschef.ui.detalle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePantalla(
    id: Int,
    navController: NavHostController,
    viewModel: DetalleViewModel = viewModel()
) {
    val detalle by viewModel.detalle.collectAsState()
    val estadisticas by viewModel.estadisticas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarDetalle(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(8.dp).background(Color.White.copy(alpha = 0.5f), CircleShape)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        detalle?.let { item ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                // 1. Imagen y Cabecera
                item {
                    AsyncImage(
                        model = item.receta.imagen ?: "https://via.placeholder.com/400",
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().height(280.dp),
                        contentScale = ContentScale.Crop
                    )
                    
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = item.receta.nombre, 
                            style = MaterialTheme.typography.headlineMedium, 
                            fontWeight = FontWeight.Bold
                        )
                        
                        Row(modifier = Modifier.padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, null, tint = Color(0xFFFFB300), modifier = Modifier.size(18.dp))
                            
                            val promedio = estadisticas?.promedio_puntuacion ?: 0.0
                            val opinionesTotal = estadisticas?.total_opiniones ?: 0
                            val preparaciones = estadisticas?.total_preparaciones ?: 0
                            
                            Text(" ${String.format(Locale.US, "%.1f", promedio)} ($opinionesTotal) ", fontWeight = FontWeight.Bold)
                            Text(" • $preparaciones prep. • ${item.receta.tiempo_preparacion} min", color = Color.Gray, fontSize = 14.sp)
                        }

                        Surface(color = Color(0xFFE8F5E9), shape = RoundedCornerShape(16.dp)) {
                            Text(
                                text = item.receta.categoria, 
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp), 
                                color = Color(0xFF2E7D32), 
                                fontWeight = FontWeight.Bold, 
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))
                        Text("Ingredientes", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                }

                // 2. Lista de Ingredientes
                items(item.ingredientes) { ing ->
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp), 
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(6.dp).background(Color(0xFF2E7D32), CircleShape))
                        Text("  ${ing.descripcion}", fontSize = 16.sp)
                    }
                }

                // 3. Preparación
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Preparación", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                }

                items(item.pasos) { paso ->
                    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Text("${paso.orden_paso}. ", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                        Text(paso.descripcion)
                    }
                }

                // 4. NUEVA SECCIÓN: Opiniones de la comunidad
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Opiniones de la comunidad", 
                            style = MaterialTheme.typography.titleLarge, 
                            fontWeight = FontWeight.Bold
                        )
                        if (item.opiniones.isEmpty()) {
                            Text(
                                text = "Aún no hay opiniones. ¡Sé el primero!", 
                                color = Color.Gray, 
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }

                items(item.opiniones) { opinion ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                repeat(5) { index ->
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = if (index < opinion.puntuacion) Color(0xFFFFB300) else Color.LightGray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${opinion.puntuacion}", 
                                    fontWeight = FontWeight.Bold, 
                                    fontSize = 14.sp
                                )
                            }
                            if (opinion.comentario.isNotBlank()) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = opinion.comentario, fontSize = 14.sp, color = Color.DarkGray)
                            }
                        }
                    }
                }

                // 5. Botón de Acción Final
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { 
                            navController.navigate("registrar_preparacion/${item.receta.id}")
                        }, 
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(54.dp), 
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Registrar mi experiencia", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}
