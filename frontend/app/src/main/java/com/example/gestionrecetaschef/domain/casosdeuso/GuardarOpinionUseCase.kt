package com.example.gestionrecetaschef.domain.casosdeuso

import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.OpinionRequest

class GuardarOpinionUseCase(
    private val repositorio: RecetasRepositorio = RecetasRepositorio()
) {
    suspend operator fun invoke(opinion: OpinionRequest) =
        repositorio.guardarOpinion(opinion)
}