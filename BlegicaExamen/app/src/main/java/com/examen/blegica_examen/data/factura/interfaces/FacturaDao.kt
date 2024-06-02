package com.examen.blegica_examen.data.factura.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.examen.blegica_examen.data.factura.Factura

import kotlinx.coroutines.flow.Flow

@Dao
interface FacturaDao {
    @Insert

    fun agregarFactura(factura: Factura)

    @Update
    suspend fun update(factura: Factura)

    @Delete
    suspend fun delete(factura: Factura)

    @Query("SELECT * FROM Facturas WHERE facturaID = :id")
    fun obtenerFacturaPorId(id: Int): Factura?

    @Query("SELECT * FROM Facturas")
    fun obtenerTodasLasFacturas(): Flow<List<Factura>>
}
