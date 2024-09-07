package com.hfad.xmldisney.di

import com.hfad.xmldisney.repository.IntDbRepository
import com.hfad.xmldisney.repository.IntDisneyRepository
import com.hfad.xmldisney.repository.impl.DbRepository
import com.hfad.xmldisney.repository.impl.DisneyRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDbRepository(
        repository: DbRepository
    ): IntDbRepository

    @Binds
    @Singleton
    abstract fun bindDisneyRepository(
        repository: DisneyRepository
    ): IntDisneyRepository
}