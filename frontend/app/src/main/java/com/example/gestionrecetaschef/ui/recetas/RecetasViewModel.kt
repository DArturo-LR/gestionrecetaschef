package com.example.gestionrecetaschef.ui.recetas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.Receta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class RecetasViewModel : ViewModel() {

    private val repositorio =
        RecetasRepositorio()

    private val _recetas =
        MutableStateFlow<List<Receta>>(
            emptyList()
        )

    val recetas =
        _recetas.asStateFlow()

    fun cargarRecetas() {

        viewModelScope.launch {

            try {

                _recetas.value =
                    repositorio.obtenerRecetas()

            } catch (e: Exception) {

                Log.e(
                    "ERROR_RECETAS",
                    e.toString()
                )
            }
        }
    }
}