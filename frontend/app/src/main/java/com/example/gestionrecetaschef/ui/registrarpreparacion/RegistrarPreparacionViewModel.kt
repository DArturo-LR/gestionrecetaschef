package com.example.gestionrecetaschef.ui.registrarpreparacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.OpinionRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.util.Log

class RegistrarPreparacionViewModel : ViewModel() {

    private val repositorio = RecetasRepositorio()

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
                val opinion = OpinionRequest(
                    receta_id = recetaId,
                    comentario = comentario,
                    puntuacion = puntuacion,
                    veces_preparada = vecesPreparada
                )
                repositorio.guardarOpinion(opinion)
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
