package com.examen.blegica_examen.data.detallefactura.interfaces
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.examen.blegica_examen.data.detallefactura.DetalleFactura
import kotlinx.coroutines.flow.Flow

@Dao
interface DetalleFacturaDao {

    @Insert
    fun agregarDetallefactura(detalleFactura: DetalleFactura)

    @Update
    suspend fun update(detalleFactura: DetalleFactura)

    @Delete
    suspend fun delete(detalleFactura: DetalleFactura)

    @Query("SELECT * FROM DetalleFactura WHERE id = :id")
    fun obtenerDetallefacturaPorId(id: Int): DetalleFactura?

    @Query("SELECT * FROM DetalleFactura")
    fun obtenerTodosLosDetallefactura(): Flow<List<DetalleFactura>>
}
