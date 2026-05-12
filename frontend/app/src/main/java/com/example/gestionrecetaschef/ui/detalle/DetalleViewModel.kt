package com.example.gestionrecetaschef.ui.detalle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionrecetaschef.data.repositorio.RecetasRepositorio
import com.example.gestionrecetaschef.domain.modelo.DetalleReceta

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch

class DetalleViewModel :
    ViewModel() {

    private val repositorio =
        RecetasRepositorio()

    private val _detalle =
        MutableStateFlow<DetalleReceta?>(
            null
        )

    val detalle =
        _detalle.asStateFlow()

    fun cargarDetalle(id: Int) {

        viewModelScope.launch {

            try {

                _detalle.value =
                    repositorio
                        .obtenerDetalleReceta(id)

            } catch (e: Exception) {

            }
        }
    }
}