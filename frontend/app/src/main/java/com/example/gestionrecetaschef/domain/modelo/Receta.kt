package com.example.gestionrecetaschef.domain.modelo

data class Receta(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val tiempo_preparacion: Int,
    val imagen: String?,
    // Campos nuevos para datos reales (opcionales para no romper nada)
    val promedio_puntuacion: Double? = 0.0,
    val total_opiniones: Int? = 0,
    val total_preparaciones: Int? = 0
)
