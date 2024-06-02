package com.examen.blegica_examen.ui.facturas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.blegica_examen.R
import com.examen.blegica_examen.data.factura.interfaces.FacturaRepository
import com.examen.blegica_examen.data.detallefactura.interfaces.DetalleFacturaRepository
import com.examen.blegica_examen.data.factura.Factura
import com.examen.blegica_examen.data.detallefactura.DetalleFactura
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FacturaViewModel(private val facturaRepository: FacturaRepository,
                         private val detalleFacturaRepository: DetalleFacturaRepository
): ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    private val insercion_imagen = listOf(
        R.drawable.imagen1,
        R.drawable.imagen2,
        R.drawable.imagen3,
    )

    private val facturas = listOf(
        Factura(numero = "F001", fecha = "2023-06-01",cliente="Juan Marco", imagenId = insercion_imagen[0]),
        Factura(numero = "F002", fecha = "2023-05-05", cliente="Annel Flores", imagenId = insercion_imagen[1]),
        Factura(numero = "F003", fecha = "2023-06-03",cliente="Marco Ayoví",imagenId = insercion_imagen[2]),
        )

    private val detalleFacturaFacturas = listOf(
        DetalleFactura(descripcion = "Factura x", cantidad = 5, precio = 190.00, facturaDetalleID =1),
        DetalleFactura(descripcion = "Factura x", cantidad = 15, precio = 300.00, facturaDetalleID=2),
        DetalleFactura(descripcion = "Factura x", cantidad = 2, precio = 200.00, facturaDetalleID=3),
 )
    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }

        viewModelScope.launch(handler) {
            val categoriaList = facturaRepository.obtenerTodasLasFacturas().firstOrNull()
            if (categoriaList == null || categoriaList.isEmpty()) {
                try {
                    facturas.forEach { factura ->
                        withContext(Dispatchers.IO) {
                            facturaRepository.agregarFactura(factura)
                        }
                    }
                } catch (e: Exception) {
                    println("Error al insertar facturas: ${e.message}")
                }
            }
        }

        viewModelScope.launch(handler) {
            val detallefacturaList = detalleFacturaRepository.obtenerTodosLosDetallefactura().firstOrNull()
            if (detallefacturaList == null || detallefacturaList.isEmpty()) {
                try {
                    detalleFacturaFacturas.forEach { detallefactura ->
                        withContext(Dispatchers.IO) {
                            detalleFacturaRepository.agregarDetallefactura(detallefactura)
                        }
                    }
                } catch (e: Exception) {
                    println("Error al insertar productos: ${e.message}")
                }
            }
        }
    }


    val facturaUiState: StateFlow<FacturaUiState> =
        facturaRepository.obtenerTodasLasFacturas().map { facturas ->
            FacturaUiState(facturas)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FacturaUiState()
            )
}


data class FacturaUiState(val facturaList: List<Factura> = listOf())
