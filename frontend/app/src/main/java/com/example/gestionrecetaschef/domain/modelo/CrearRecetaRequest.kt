package com.example.gestionrecetaschef.domain.modelo

data class CrearRecetaRequest(
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val tiempo_preparacion: Int,
    val imagen: String?,
    val ingredientes: List<String>,
    val pasos: List<String>
)
