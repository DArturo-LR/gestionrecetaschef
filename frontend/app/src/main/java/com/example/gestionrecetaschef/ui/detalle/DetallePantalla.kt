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
    val estadisticas by
    viewModel.estadisticas.collectAsState()

    LaunchedEffect(Unit) {

        viewModel.cargarDetalle(id)
    }
    var comentario by remember {
        mutableStateOf("")
    }

    var puntuacion by remember {
        mutableStateOf("")
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
                        Modifier.height(8.dp)
                )

                estadisticas?.let {

                    Text(
                        text =
                            "Promedio puntuación: ${it.promedio_puntuacion}"
                    )

                    Text(
                        text =
                            "Opiniones registradas: ${it.total_opiniones}"
                    )
                    Text(
                        text =
                            "Veces preparada: ${it.total_preparaciones}"
                    )
                }

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
            item {

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                Text(
                    text = "Agregar opinión",

                    style =
                        MaterialTheme
                            .typography
                            .titleLarge
                )

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                OutlinedTextField(

                    value = comentario,

                    onValueChange = {
                        comentario = it
                    },

                    modifier = Modifier
                        .fillMaxWidth(),

                    label = {
                        Text("Comentario")
                    }
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                OutlinedTextField(

                    value = puntuacion,

                    onValueChange = {
                        puntuacion = it
                    },

                    modifier = Modifier
                        .fillMaxWidth(),

                    label = {
                        Text("Puntuación")
                    }
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )


                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Button(

                    onClick = {

                        if (

                            comentario.isNotBlank()

                            &&

                            puntuacion.isNotBlank()

                        ) {

                            viewModel.guardarOpinion(

                                id,

                                comentario,

                                puntuacion.toDoubleOrNull() ?: 0.0

                            )

                            comentario = ""
                            puntuacion = ""
                        }
                    }

                ) {

                    Text(
                        text = "Guardar opinión"
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
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