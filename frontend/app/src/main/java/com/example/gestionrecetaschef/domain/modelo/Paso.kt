package com.example.gestionrecetaschef.domain.modelo

data class Paso(
    val id: Int,
    val receta_id: Int,
    val descripcion: String,
    val orden_paso: Int
)