package com.example.gestionrecetaschef.data.repositorio

import com.example.gestionrecetaschef.data.remoto.RetrofitCliente
import com.example.gestionrecetaschef.domain.modelo.OpinionRequest

class RecetasRepositorio {

    suspend fun obtenerRecetas() =
        RetrofitCliente
            .servicio
            .obtenerRecetas()
    suspend fun obtenerDetalleReceta(
        id: Int
    ) =
        RetrofitCliente
            .servicio
            .obtenerDetalleReceta(id)
    suspend fun guardarOpinion(
        opinion: OpinionRequest
    ) =
        RetrofitCliente
            .servicio
            .guardarOpinion(opinion)
}