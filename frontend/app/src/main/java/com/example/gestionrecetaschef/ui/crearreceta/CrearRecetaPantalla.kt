package com.example.gestionrecetaschef.ui.crearreceta



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.gestionrecetaschef.domain.modelo.CrearRecetaRequest

@Composable
fun CrearRecetaPantalla(

    viewModel: CrearRecetaViewModel =
        viewModel()

) {

    var nombre by remember {
        mutableStateOf("")
    }

    var descripcion by remember {
        mutableStateOf("")
    }

    var categoria by remember {
        mutableStateOf("")
    }

    var tiempoPreparacion by remember {
        mutableStateOf("")
    }

    var imagenUrl by remember {
        mutableStateOf("")
    }
    var ingrediente by remember {
        mutableStateOf("")
    }

    var listaIngredientes by remember {
        mutableStateOf(listOf<String>())
    }

    var paso by remember {
        mutableStateOf("")
    }

    var listaPasos by remember {
        mutableStateOf(listOf<String>())
    }

    var comentario by remember {
        mutableStateOf("")
    }

    var puntuacion by remember {
        mutableStateOf("")
    }

    var vecesPreparada by remember {
        mutableStateOf("")
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(10.dp)

    ) {

        Text(

            text = "Crear receta",

            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        OutlinedTextField(

            value = nombre,

            onValueChange = {
                nombre = it
            },

            modifier = Modifier
                .fillMaxWidth(),

            label = {
                Text("Nombre")
            }
        )

        OutlinedTextField(

            value = descripcion,

            onValueChange = {
                descripcion = it
            },

            modifier = Modifier
                .fillMaxWidth(),

            label = {
                Text("Descripción")
            }
        )

        OutlinedTextField(

            value = categoria,

            onValueChange = {
                categoria = it
            },

            modifier = Modifier
                .fillMaxWidth(),

            label = {
                Text("Categoría")
            }
        )

        OutlinedTextField(

            value = imagenUrl,

            onValueChange = {
                imagenUrl = it
            },

            modifier = Modifier
                .fillMaxWidth(),

            label = {
                Text("URL de imagen")
            }
        )
        OutlinedTextField(

            value = ingrediente,

            onValueChange = {
                ingrediente = it
            },

            label = {
                Text("Ingrediente")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Button(

            onClick = {

                if (ingrediente.isNotBlank()) {

                    listaIngredientes =
                        listaIngredientes + ingrediente

                    ingrediente = ""
                }
            }

        ) {

            Text("Agregar ingrediente")
        }
        listaIngredientes.forEach {

            Text("• $it")
        }
        OutlinedTextField(

            value = paso,

            onValueChange = {
                paso = it
            },

            label = {
                Text("Paso")
            },

            modifier = Modifier.fillMaxWidth()
        )
        Button(

            onClick = {

                if (paso.isNotBlank()) {

                    listaPasos =
                        listaPasos + paso

                    paso = ""
                }
            }

        ) {

            Text("Agregar paso")
        }
        listaPasos.forEachIndexed { index, item ->

            Text("${index + 1}. $item")
        }
        OutlinedTextField(
            value = comentario,
            onValueChange = {
                comentario = it
            },
            label = {
                Text("Comentario inicial")
            }
        )
        OutlinedTextField(
            value = puntuacion,
            onValueChange = {
                puntuacion = it
            },
            label = {
                Text("Puntuación")
            }
        )
        OutlinedTextField(
            value = vecesPreparada,
            onValueChange = {
                vecesPreparada = it
            },
            label = {
                Text("Veces preparada")
            }
        )

        OutlinedTextField(

            value = tiempoPreparacion,

            onValueChange = {
                tiempoPreparacion = it
            },

            modifier = Modifier
                .fillMaxWidth(),

            label = {
                Text("Tiempo preparación")
            }
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        Button(

            onClick = {

                if (

                    nombre.isNotBlank()

                    &&

                    descripcion.isNotBlank()

                    &&

                    categoria.isNotBlank()

                    &&

                    tiempoPreparacion.isNotBlank()

                ) {

                    val receta =

                        CrearRecetaRequest(

                            nombre = nombre,

                            descripcion = descripcion,

                            categoria = categoria,

                            tiempo_preparacion =
                                tiempoPreparacion.toInt(),

                            imagen = imagenUrl,

                            ingredientes =
                                listaIngredientes,

                            pasos =
                                listaPasos,

                            comentario =
                                comentario,

                            puntuacion =
                                puntuacion.toDouble(),

                            veces_preparada =
                                vecesPreparada.toInt()
                        )

                    viewModel.crearReceta(
                        receta
                    )

                    nombre = ""
                    descripcion = ""
                    categoria = ""
                    tiempoPreparacion = ""
                    imagenUrl = ""
                }
            }

        ) {

            Text(
                text = "Guardar receta"
            )
        }
    }
}