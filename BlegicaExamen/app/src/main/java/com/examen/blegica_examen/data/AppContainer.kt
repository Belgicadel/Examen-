package com.examen.blegica_examen.data

import android.content.Context
import com.examen.blegica_examen.data.factura.OfflineFacturaRepository
import com.examen.blegica_examen.data.factura.interfaces.FacturaRepository
import com.examen.blegica_examen.data.detallefactura.OfflineDetalleFacturaRepository
import com.examen.blegica_examen.data.detallefactura.interfaces.DetalleFacturaRepository

interface AppContainer {
    val facturaRepository: FacturaRepository
    val detalleFacturaRepository: DetalleFacturaRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val facturaRepository: FacturaRepository by lazy {
        OfflineFacturaRepository(CategoriaDatabase.getDatabase(context).categoriaDao())
    }

    /**
     * Implementation for [HeladoDao]
     */
    override val detalleFacturaRepository: DetalleFacturaRepository by lazy {
        OfflineDetalleFacturaRepository(CategoriaDatabase.getDatabase(context).movimientoDao())
    }

}
