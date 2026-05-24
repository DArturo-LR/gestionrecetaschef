package com.example.gestionrecetaschef.domain.modelo

data class OpinionRequest(
    val receta_id: Int,
    val comentario: String,
    val puntuacion: Double,
    val veces_preparada: Int
)
