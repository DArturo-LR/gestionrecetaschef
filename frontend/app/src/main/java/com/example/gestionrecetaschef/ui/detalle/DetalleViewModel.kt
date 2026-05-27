package com.example.gestionrecetaschef.ui.detalle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.domain.casosdeuso.GuardarOpinionUseCase
import com.example.gestionrecetaschef.domain.casosdeuso.ObtenerDetalleRecetaUseCase
import com.example.gestionrecetaschef.domain.casosdeuso.ObtenerEstadisticasUseCase
import com.example.gestionrecetaschef.domain.modelo.DetalleReceta
import com.example.gestionrecetaschef.domain.modelo.Estadisticas
import com.example.gestionrecetaschef.domain.modelo.OpinionRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetalleViewModel(
    private val obtenerDetalleRecetaUseCase: ObtenerDetalleRecetaUseCase = ObtenerDetalleRecetaUseCase(),
    private val obtenerEstadisticasUseCase: ObtenerEstadisticasUseCase = ObtenerEstadisticasUseCase(),
    private val guardarOpinionUseCase: GuardarOpinionUseCase = GuardarOpinionUseCase()
) : ViewModel() {

    private val _detalle = MutableStateFlow<DetalleReceta?>(null)
    val detalle = _detalle.asStateFlow()

    private val _estadisticas = MutableStateFlow<Estadisticas?>(null)
    val estadisticas = _estadisticas.asStateFlow()

    fun cargarDetalle(id: Int) {
        viewModelScope.launch {
            try {
                _detalle.value = obtenerDetalleRecetaUseCase(id)
                _estadisticas.value = obtenerEstadisticasUseCase(id)
            } catch (e: Exception) {
                Log.e("ERROR_DETALLE", e.toString())
            }
        }
    }

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
                cargarDetalle(recetaId)
            } catch (e: Exception) {
                Log.e("ERROR_GUARDAR_OPINION", e.toString())
            }
        }
    }
}
