package com.example.gestionrecetaschef.domain.modelo



data class CrearRecetaRequest(
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val tiempo_preparacion: Int,
    val ingredientes: List<String>,
    val pasos: List<String>,
    val comentario: String,
    val puntuacion: Double,
    val veces_preparada: Int
)