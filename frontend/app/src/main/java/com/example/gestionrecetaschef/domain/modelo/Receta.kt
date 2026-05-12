package com.example.gestionrecetaschef.domain.modelo

data class Receta(

    val id: Int,

    val nombre: String,

    val descripcion: String,

    val categoria: String,

    val tiempo_preparacion: Int,

    val imagen: String
)