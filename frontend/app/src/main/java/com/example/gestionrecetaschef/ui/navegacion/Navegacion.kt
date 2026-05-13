package com.example.gestionrecetaschef.ui.navegacion

import androidx.compose.runtime.Composable

import androidx.navigation.compose.*
import com.example.gestionrecetaschef.ui.crearreceta.CrearRecetaPantalla
import com.example.gestionrecetaschef.ui.detalle.DetallePantalla
import com.example.gestionrecetaschef.ui.recetas.RecetasPantalla

@Composable
fun Navegacion() {

    val navController =
        rememberNavController()

    NavHost(


        navController = navController,

        startDestination =
            "recetas"

    ) {

        composable("recetas") {

            RecetasPantalla(
                navController
            )
        }
        composable("crear") {

            CrearRecetaPantalla()
        }

        composable(
            "detalle/{id}"
        ) {

            val id =
                it.arguments
                    ?.getString("id")
                    ?.toInt()

            if (id != null) {

                DetallePantalla(id)
            }
        }
    }
}