package com.example.gestionrecetaschef.domain.casosdeuso

import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.DetalleReceta

class ObtenerDetalleRecetaUseCase(private val repositorio: RecetasRepositorio = RecetasRepositorio()
) {
    suspend operator fun invoke(id: Int): DetalleReceta = repositorio.obtenerDetalleReceta(id)
}