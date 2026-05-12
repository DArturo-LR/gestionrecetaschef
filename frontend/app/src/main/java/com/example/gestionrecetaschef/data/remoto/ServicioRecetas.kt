package com.example.gestionrecetaschef.data.remoto

import com.example.gestionrecetaschef.domain.modelo.DetalleReceta
import com.example.gestionrecetaschef.domain.modelo.Receta
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicioRecetas {

    @GET("recetas")
    suspend fun obtenerRecetas():
            List<Receta>

    @GET("recetas/{id}")
    suspend fun obtenerDetalleReceta(

        @Path("id")
        id: Int

    ): DetalleReceta
}