package com.example.gestionrecetaschef.data.remoto

import com.example.gestionrecetaschef.domain.modelo.DetalleReceta
import com.example.gestionrecetaschef.domain.modelo.Estadisticas
import com.example.gestionrecetaschef.domain.modelo.OpinionRequest
import com.example.gestionrecetaschef.domain.modelo.Receta
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicioRecetas {

    @GET("recetas")
    suspend fun obtenerRecetas():
            List<Receta>

    @GET("recetas/{id}")
    suspend fun obtenerDetalleReceta(

        @Path("id")
        id: Int

    ): DetalleReceta
    @POST("opiniones")
    suspend fun guardarOpinion(

        @Body
        opinion: OpinionRequest

    )
    @GET("recetas/{id}/estadisticas")
    suspend fun obtenerEstadisticas(

        @Path("id")
        id: Int

    ): Estadisticas
}