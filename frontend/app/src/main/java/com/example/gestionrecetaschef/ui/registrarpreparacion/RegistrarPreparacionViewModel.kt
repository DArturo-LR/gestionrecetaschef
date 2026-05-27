package com.example.gestionrecetaschef.ui.registrarpreparacion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.domain.casosdeuso.GuardarOpinionUseCase
import com.example.gestionrecetaschef.domain.modelo.OpinionRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegistrarPreparacionViewModel(
    private val guardarOpinionUseCase: GuardarOpinionUseCase = GuardarOpinionUseCase()
) : ViewModel() {

    private val _guardado = MutableStateFlow(false)
    val guardado = _guardado.asStateFlow()

    fun guardarOpinion(
        recetaId: Int,
        comentario: String,
        puntuacion: Double,
        vecesPreparada: Int
    ) {
        viewModelScope.launch {
            try {
                guardarOpinionUseCase(
                    OpinionRequest(
                        receta_id = recetaId,
                        comentario = comentario,
                        puntuacion = puntuacion,
                        veces_preparada = vecesPreparada
                    )
                )
                _guardado.value = true
            } catch (e: Exception) {
                Log.e("ERROR_GUARDAR_OPINION", e.toString())
            }
        }
    }

    fun resetGuardado() {
        _guardado.value = false
    }
}
