package com.hfad.xmldisney

import android.app.Application
import com.hfad.xmldisney.di.AppComponent
import com.hfad.xmldisney.di.AppModule
import com.hfad.xmldisney.di.DaggerAppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }

    companion object {

        var appComponent: AppComponent? = null
    }
}