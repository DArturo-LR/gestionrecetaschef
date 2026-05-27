package com.example.gestionrecetaschef.domain.casosdeuso

import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.Receta

class ObtenerRecetasUseCase(private val repositorio: RecetasRepositorio = RecetasRepositorio()
) {
    suspend operator fun invoke(): List<Receta> = repositorio.obtenerRecetas()
}