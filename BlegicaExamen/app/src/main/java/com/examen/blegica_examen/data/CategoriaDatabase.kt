package com.examen.blegica_examen.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.examen.blegica_examen.data.detallefactura.interfaces.DetalleFacturaDao
import com.examen.blegica_examen.data.factura.interfaces.FacturaDao
import com.examen.blegica_examen.data.factura.Factura
import com.examen.blegica_examen.data.detallefactura.DetalleFactura

@Database(
    entities = [
        Factura::class,
        DetalleFactura::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CategoriaDatabase : RoomDatabase() {

    abstract fun movimientoDao(): DetalleFacturaDao
    abstract fun categoriaDao(): FacturaDao

    companion object {
        @Volatile
        private var INSTANCE: CategoriaDatabase? = null

        fun getDatabase(context: Context): CategoriaDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CategoriaDatabase::class.java,
                    "categorias"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
