package com.examen.blegica_examen.data.factura.interfaces

import com.examen.blegica_examen.data.factura.Factura
import kotlinx.coroutines.flow.Flow

interface FacturaRepository {

    fun obtenerTodasLasFacturas(): Flow<List<Factura>>

    fun obtenerFacturaPorId(id: Int): Factura?

    suspend fun agregarFactura(factura: Factura)

    suspend fun delete(factura: Factura)

    suspend fun update(factura: Factura)

}