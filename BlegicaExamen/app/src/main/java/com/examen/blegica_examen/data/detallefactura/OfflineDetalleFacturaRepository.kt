package com.examen.blegica_examen.data.detallefactura

import com.examen.blegica_examen.data.detallefactura.interfaces.DetalleFacturaDao
import com.examen.blegica_examen.data.detallefactura.interfaces.DetalleFacturaRepository
import kotlinx.coroutines.flow.Flow


class OfflineDetalleFacturaRepository (private val detalleFacturaDao: DetalleFacturaDao) :
    DetalleFacturaRepository {

    override fun obtenerDetallefacturaPorId(id: Int): DetalleFactura? = detalleFacturaDao.obtenerDetallefacturaPorId(id)

    override fun obtenerTodosLosDetallefactura(): Flow<List<DetalleFactura>> = detalleFacturaDao.obtenerTodosLosDetallefactura()

    override fun agregarDetallefactura(detalleFactura: DetalleFactura) = detalleFacturaDao.agregarDetallefactura(detalleFactura)

    override suspend fun update(detalleFactura: DetalleFactura) = detalleFacturaDao.update(detalleFactura)

    override suspend fun delete(detalleFactura: DetalleFactura) = detalleFacturaDao.delete(detalleFactura)

}