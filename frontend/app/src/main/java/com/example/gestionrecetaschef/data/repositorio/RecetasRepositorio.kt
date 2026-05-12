package com.example.gestionrecetaschef.data.repositorio

import com.example.gestionrecetaschef.data.remoto.RetrofitCliente

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
}