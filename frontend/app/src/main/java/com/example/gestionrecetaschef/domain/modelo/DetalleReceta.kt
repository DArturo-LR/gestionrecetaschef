package com.example.gestionrecetaschef.domain.modelo

data class DetalleReceta(
    val receta: Receta,
    val ingredientes: List<Ingrediente>,
    val pasos: List<Paso>,
    val opiniones: List<Opinion>
)