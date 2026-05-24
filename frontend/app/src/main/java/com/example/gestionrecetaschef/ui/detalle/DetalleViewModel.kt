package com.example.gestionrecetaschef.ui.detalle

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.DetalleReceta
import com.example.gestionrecetaschef.domain.modelo.Estadisticas
import com.example.gestionrecetaschef.domain.modelo.OpinionRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetalleViewModel : ViewModel() {

    private val repositorio = RecetasRepositorio()

    private val _detalle = MutableStateFlow<DetalleReceta?>(null)
    val detalle = _detalle.asStateFlow()

    private val _estadisticas = MutableStateFlow<Estadisticas?>(null)
    val estadisticas = _estadisticas.asStateFlow()

    fun cargarDetalle(id: Int) {
        viewModelScope.launch {
            try {
                _detalle.value = repositorio.obtenerDetalleReceta(id)
                _estadisticas.value = repositorio.obtenerEstadisticas(id)
            } catch (e: Exception) {
                Log.e("ERROR_DETALLE", "Error al cargar: ${e.message}")
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
                // Usamos parámetros nombrados para que coincida exactamente con el modelo
                repositorio.guardarOpinion(
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
