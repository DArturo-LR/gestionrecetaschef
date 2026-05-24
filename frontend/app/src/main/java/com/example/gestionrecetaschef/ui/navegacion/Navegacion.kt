package com.example.gestionrecetaschef.ui.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.gestionrecetaschef.ui.crearreceta.CrearRecetaPantalla
import com.example.gestionrecetaschef.ui.detalle.DetallePantalla
import com.example.gestionrecetaschef.ui.recetas.RecetasPantalla
import com.example.gestionrecetaschef.ui.registrarpreparacion.RegistrarPreparacionPantalla

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Solo mostramos el BottomBar en las pantallas principales
            if (currentRoute == "recetas" || currentRoute == "crear") {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio") },
                        selected = currentRoute == "recetas",
                        onClick = {
                            navController.navigate("recetas") {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )

                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Add, contentDescription = "Agregar") },
                        label = { Text("Agregar") },
                        selected = currentRoute == "crear",
                        onClick = {
                            navController.navigate("crear") {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )

                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") },
                        selected = false,
                        onClick = { /* No hace nada */ }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "recetas",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("recetas") {
                RecetasPantalla(navController)
            }

            composable("crear") {
                CrearRecetaPantalla(navController = navController)
            }

            composable("detalle/{id}") {
                val id = it.arguments?.getString("id")?.toInt()
                if (id != null) {
                    DetallePantalla(id, navController)
                }
            }

            composable("registrar_preparacion/{id}") {
                val id = it.arguments?.getString("id")?.toInt()
                if (id != null) {
                    RegistrarPreparacionPantalla(id, navController)
                }
            }
        }
    }
}
