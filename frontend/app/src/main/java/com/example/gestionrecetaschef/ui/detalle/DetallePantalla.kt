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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePantalla(
    id: Int,
    navController: NavHostController,
    viewModel: DetalleViewModel = viewModel()
) {
    val detalle by viewModel.detalle.collectAsState()

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
                            Text(" 4.7 (23) ", fontWeight = FontWeight.Bold)
                            Text(" • 3 porciones • 30 min • Fácil", color = Color.Gray, fontSize = 14.sp)
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

                items(item.ingredientes) { ing ->
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp), 
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(6.dp).background(Color(0xFF2E7D32), CircleShape))
                        Text("  ${ing.descripcion}", fontSize = 16.sp)
                    }
                }

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

                item {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(), 
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = { /* Favorito */ }, 
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        ) { 
                            Text("Favorito") 
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { 
                                // NAVEGACIÓN A LA NUEVA PANTALLA
                                navController.navigate("registrar_preparacion/${item.receta.id}")
                            }, 
                            modifier = Modifier.weight(2f), 
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Registrar experiencia", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
