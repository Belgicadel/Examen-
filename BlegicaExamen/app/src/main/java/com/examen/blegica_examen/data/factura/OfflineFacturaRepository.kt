package com.examen.blegica_examen.data.factura

import com.examen.blegica_examen.data.factura.interfaces.FacturaDao
import com.examen.blegica_examen.data.factura.interfaces.FacturaRepository
import kotlinx.coroutines.flow.Flow

class OfflineFacturaRepository(private val facturaDao: FacturaDao) : FacturaRepository {

        override fun obtenerTodasLasFacturas(): Flow<List<Factura>> = facturaDao.obtenerTodasLasFacturas()

        override fun obtenerFacturaPorId(id: Int): Factura? = facturaDao.obtenerFacturaPorId(id)

        override suspend fun agregarFactura(factura: Factura) = facturaDao.agregarFactura(factura)

        override suspend fun delete(factura: Factura) = facturaDao.delete(factura)

        override suspend fun update(factura: Factura) = facturaDao.update(factura)

}
