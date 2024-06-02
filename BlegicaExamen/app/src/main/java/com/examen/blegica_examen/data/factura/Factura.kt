package com.examen.blegica_examen.data.factura

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Facturas")
data class Factura(
    val numero: String,
    val fecha: String,
    val cliente: String,
    val imagenId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var FacturaID: Int = 0
}
