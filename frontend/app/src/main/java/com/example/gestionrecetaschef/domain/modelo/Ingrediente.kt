package com.example.gestionrecetaschef.domain.modelo

data class Ingrediente(
    val id: Int,
    val receta_id: Int,
    val descripcion: String
)