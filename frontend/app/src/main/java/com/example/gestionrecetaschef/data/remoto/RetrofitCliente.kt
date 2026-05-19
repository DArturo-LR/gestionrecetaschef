package com.example.gestionrecetaschef.data.remoto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCliente {

   private const val BASE_URL = "http://192.168.20.26:3000/"

    val servicio: ServicioRecetas by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(ServicioRecetas::class.java)
    }
}
