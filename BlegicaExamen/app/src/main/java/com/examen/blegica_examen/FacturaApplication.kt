package com.examen.blegica_examen

import android.app.Application
import com.examen.blegica_examen.data.AppContainer
import com.examen.blegica_examen.data.AppDataContainer

class FacturaApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
