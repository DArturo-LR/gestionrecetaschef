package com.example.gestionrecetaschef.domain.modelo

data class Opinion(
    val id: Int,
    val receta_id: Int,
    val comentario: String,
    val puntuacion: Int,
    val veces_preparada: Int
)