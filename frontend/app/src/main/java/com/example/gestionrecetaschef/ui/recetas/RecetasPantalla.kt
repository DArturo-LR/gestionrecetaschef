package com.example.gestionrecetaschef.ui.recetas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImage
import androidx.navigation.compose.currentBackStackEntryAsState
@Composable
fun RecetasPantalla(
    navController: NavHostController,
    viewModel: RecetasViewModel = viewModel()
) {

    val recetas by viewModel.recetas.collectAsState()

    val backStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(backStackEntry) {
        viewModel.cargarRecetas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        Button(
            onClick = {
                navController.navigate("crear")
            }
        ) {
            Text(text = "Nueva receta")
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(recetas) { receta ->

                Card(
                    onClick = {
                        navController.navigate("detalle/${receta.id}")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {

                        if (!receta.imagen.isNullOrBlank()) {

                            AsyncImage(
                                model = receta.imagen,
                                contentDescription = receta.nombre,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        Text(
                            text = receta.nombre,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(text = "Categoría: ${receta.categoria}")

                        Text(text = "Tiempo: ${receta.tiempo_preparacion} min")

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(text = receta.descripcion)
                    }
                }
            }
        }
    }
}
