package com.example.gestionrecetaschef.domain.casosdeuso

import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.CrearRecetaRequest

class CrearRecetaUseCase(
    private val repositorio: RecetasRepositorio = RecetasRepositorio()
) {
    suspend operator fun invoke(receta: CrearRecetaRequest) =
        repositorio.crearReceta(receta)
}