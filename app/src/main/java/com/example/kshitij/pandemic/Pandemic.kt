package com.example.kshitij.pandemic

import android.app.Application
import org.koin.android.ext.android.startKoin

class Pandemic: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this,
            listOf(mainModule),
            loadPropertiesFromFile = true)
    }
}