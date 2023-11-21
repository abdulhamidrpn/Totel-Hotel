package com.rpn.totel.ui.activity

import android.app.Application
import android.content.Context
import com.rpn.totel.utils.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
        var isAppRunning = false
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        appContext = this
        val modules = listOf(
            utilsModule
        )
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}

