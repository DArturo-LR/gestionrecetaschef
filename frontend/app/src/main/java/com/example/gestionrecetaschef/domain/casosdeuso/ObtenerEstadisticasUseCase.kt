package com.example.gestionrecetaschef.domain.casosdeuso

import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.Estadisticas

class ObtenerEstadisticasUseCase(private val repositorio: RecetasRepositorio = RecetasRepositorio()
) {
    suspend operator fun invoke(id: Int): Estadisticas = repositorio.obtenerEstadisticas(id)
}
