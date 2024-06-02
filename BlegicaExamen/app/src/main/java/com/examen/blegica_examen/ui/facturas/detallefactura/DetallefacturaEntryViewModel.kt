package com.examen.blegica_examen.ui.facturas.detallefactura

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.blegica_examen.data.detallefactura.DetalleFactura
import com.examen.blegica_examen.data.detallefactura.interfaces.DetalleFacturaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetallefacturaEntryViewModel(
    private val detalleFacturaRepository: DetalleFacturaRepository
) : ViewModel() {

    private val _detallefacturaUiState = MutableStateFlow(DetallefacturaUiState())
    val detallefacturaUiState: StateFlow<DetallefacturaUiState> = _detallefacturaUiState

    init {
        loadxd()
    }

    private fun loadxd() {
        viewModelScope.launch {
            _detallefacturaUiState.value = _detallefacturaUiState.value.copy(isLoading = true)
            try {
                detalleFacturaRepository.obtenerTodosLosDetallefactura().collect { detallefactura ->
                    _detallefacturaUiState.value = DetallefacturaUiState(detalleFactura = detallefactura)
                }
            } catch (e: Exception) {
                _detallefacturaUiState.value = _detallefacturaUiState.value.copy(errorMessage = e.message)
            } finally {
                _detallefacturaUiState.value = _detallefacturaUiState.value.copy(isLoading = false)
            }
        }
    }

    data class DetallefacturaUiState(
        val detalleFactura: List<DetalleFactura> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

}
