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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecetasPantalla(
    navController: NavHostController,
    viewModel: RecetasViewModel =
        viewModel()

) {

    val recetas by
    viewModel.recetas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarRecetas()
    }

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),

        verticalArrangement =
            Arrangement.spacedBy(10.dp)

    ) {

        items(recetas) { receta ->

            Card(
                onClick = {

                    navController.navigate(
                        "detalle/${receta.id}"
                    )
                },


                modifier = Modifier
                    .fillMaxWidth(),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    )

            ) {


                Column(

                    modifier = Modifier
                        .padding(12.dp)

                ) {

                    Text(
                        text = receta.nombre,

                        style =
                            MaterialTheme
                                .typography
                                .titleLarge
                    )

                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )

                    Text(
                        text =
                            "Categoría: ${receta.categoria}"
                    )

                    Text(
                        text =
                            "Tiempo: ${receta.tiempo_preparacion} min"
                    )

                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )

                    Text(
                        text = receta.descripcion
                    )

                }
            }
        }
    }
}