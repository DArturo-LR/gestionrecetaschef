package com.example.gestionrecetaschef.ui.detalle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetallePantalla(

    id: Int,

    viewModel: DetalleViewModel =
        viewModel()

) {

    val detalle by
    viewModel.detalle.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.cargarDetalle(id)
    }

    detalle?.let {

        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),

            verticalArrangement =
                Arrangement.spacedBy(10.dp)

        ) {

            item {

                Text(
                    text =
                        it.receta.nombre,

                    style =
                        MaterialTheme
                            .typography
                            .headlineMedium
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                Text(
                    text =
                        it.receta.descripcion
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Text(
                    text = "Ingredientes",
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge
                )
            }

            items(
                it.ingredientes
            ) { ingrediente ->

                Text(
                    text =
                        "• ${ingrediente.descripcion}"
                )
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Text(
                    text = "Pasos",
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge
                )
            }

            items(it.pasos) { paso ->

                Text(
                    text =
                        "${paso.orden_paso}. ${paso.descripcion}"
                )
            }

            item {

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Text(
                    text = "Opiniones",
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge
                )
            }

            items(
                it.opiniones
            ) { opinion ->

                Card {

                    Column(
                        modifier =
                            Modifier.padding(10.dp)
                    ) {

                        Text(
                            text =
                                opinion.comentario
                        )

                        Text(
                            text =
                                "Puntuación: ${opinion.puntuacion}"
                        )
                    }
                }
            }
        }
    }
}