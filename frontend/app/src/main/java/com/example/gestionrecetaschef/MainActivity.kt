package com.example.gestionrecetaschef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gestionrecetaschef.ui.navegacion.Navegacion
import com.example.gestionrecetaschef.ui.theme.GestionrecetaschefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Aplicamos tu tema personalizado aquí
            GestionrecetaschefTheme {
                Navegacion()
            }
        }
    }
}
