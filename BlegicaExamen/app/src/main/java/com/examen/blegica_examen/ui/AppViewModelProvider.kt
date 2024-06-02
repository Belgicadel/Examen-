package com.examen.blegica_examen.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.examen.blegica_examen.FacturaApplication
import com.examen.blegica_examen.ui.facturas.FacturaViewModel
import com.examen.blegica_examen.ui.facturas.detallefactura.DetallefacturaEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { DetallefacturaEntryViewModel(FacturaApplication().container.detalleFacturaRepository)
        }
        initializer { FacturaViewModel(FacturaApplication().container.facturaRepository,FacturaApplication().container.detalleFacturaRepository)
        }
}

fun CreationExtras.FacturaApplication(): FacturaApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as FacturaApplication)

}
