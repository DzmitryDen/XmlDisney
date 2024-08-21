package com.hfad.xmldisney.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "hero_db"

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(context, AppDB::class.java, DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideDisneyHeroDao(appDataBase: AppDB): DisneyHeroDao {
        return appDataBase.getDisneyHeroDao()
    }
}