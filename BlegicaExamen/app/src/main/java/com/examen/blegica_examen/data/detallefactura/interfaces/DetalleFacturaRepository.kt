package com.examen.blegica_examen.data.detallefactura.interfaces

import com.examen.blegica_examen.data.detallefactura.DetalleFactura
import kotlinx.coroutines.flow.Flow

interface DetalleFacturaRepository {

    fun obtenerDetallefacturaPorId(id: Int): DetalleFactura?

    fun obtenerTodosLosDetallefactura(): Flow<List<DetalleFactura>>

    fun agregarDetallefactura(detalleFactura: DetalleFactura)

    suspend fun update(detalleFactura: DetalleFactura)

    suspend fun delete(detalleFactura: DetalleFactura)
}