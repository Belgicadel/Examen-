package com.examen.blegica_examen.data.detallefactura

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.examen.blegica_examen.data.factura.Factura


@Entity(
    tableName = "DetalleFactura",
    foreignKeys = [ForeignKey(
        entity = Factura::class,
        parentColumns = ["FacturaID"],
        childColumns = ["facturaDetalleID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class DetalleFactura(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val descripcion: String,
    val cantidad: Int,
    val precio: Double,
    val facturaDetalleID: Int// Llave foránea
)
