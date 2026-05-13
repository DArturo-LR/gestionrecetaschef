package com.example.gestionrecetaschef.ui.crearreceta

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.CrearRecetaRequest
import kotlinx.coroutines.launch

class CrearRecetaViewModel : ViewModel() {

    private val repositorio =
        RecetasRepositorio()

    fun crearReceta(
        receta: CrearRecetaRequest
    ) {

        viewModelScope.launch {

            try {

                repositorio.crearReceta(
                    receta
                )

            } catch (e: Exception) {

                Log.e(
                    "ERROR_CREAR_RECETA",
                    e.toString()
                )
            }
        }
    }
}