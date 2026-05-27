package com.example.gestionrecetaschef.ui.crearreceta

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.domain.casosdeuso.CrearRecetaUseCase
import com.example.gestionrecetaschef.domain.modelo.CrearRecetaRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CrearRecetaViewModel(
    private val crearRecetaUseCase: CrearRecetaUseCase = CrearRecetaUseCase()
) : ViewModel() {

    private val _guardado = MutableStateFlow(false)
    val guardado = _guardado.asStateFlow()

    fun crearReceta(receta: CrearRecetaRequest) {
        viewModelScope.launch {
            try {
                crearRecetaUseCase(receta)
                _guardado.value = true
            } catch (e: Exception) {
                Log.e("ERROR_CREAR_RECETA", e.toString())
            }
        }
    }

    fun resetGuardado() {
        _guardado.value = false
    }
}
