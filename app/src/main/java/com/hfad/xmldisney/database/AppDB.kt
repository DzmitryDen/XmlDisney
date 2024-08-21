package com.hfad.xmldisney.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hfad.xmldisney.database.entity.DisneyHeroDbEntity

@Database(
    version = 1,
    entities = [
        DisneyHeroDbEntity::class
    ]
)
abstract class AppDB : RoomDatabase() {

    abstract fun getDisneyHeroDao(): DisneyHeroDao
}