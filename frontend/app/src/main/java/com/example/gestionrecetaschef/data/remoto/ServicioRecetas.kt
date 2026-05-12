package com.example.gestionrecetaschef.data.remoto

import com.example.gestionrecetaschef.domain.modelo.Receta
import retrofit2.http.GET

interface ServicioRecetas {

    @GET("recetas")
    suspend fun obtenerRecetas():
            List<Receta>
}