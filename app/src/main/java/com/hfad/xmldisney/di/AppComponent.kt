package com.hfad.xmldisney.di

import com.hfad.xmldisney.database.DataBaseModule
import com.hfad.xmldisney.network.RetrofitModule
import com.hfad.xmldisney.ui.details.DetailsFragment
import com.hfad.xmldisney.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class, RetrofitModule::class, AppModule::class])
interface AppComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: DetailsFragment)
}