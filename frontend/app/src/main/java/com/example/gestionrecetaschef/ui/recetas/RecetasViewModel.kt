package com.example.gestionrecetaschef.ui.recetas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.domain.casosdeuso.ObtenerRecetasUseCase
import com.example.gestionrecetaschef.domain.modelo.Receta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecetasViewModel(
    private val obtenerRecetasUseCase: ObtenerRecetasUseCase = ObtenerRecetasUseCase()
) : ViewModel() {

    private val _recetas = MutableStateFlow<List<Receta>>(emptyList())
    val recetas = _recetas.asStateFlow()

    fun cargarRecetas() {
        viewModelScope.launch {
            try {
                _recetas.value = obtenerRecetasUseCase()
            } catch (e: Exception) {
                Log.e("ERROR_RECETAS", e.toString())
            }
        }
    }
}