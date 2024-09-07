package com.hfad.xmldisney.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hfad.xmldisney.database.entity.DisneyHeroDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DisneyHeroDao {

    @Query("SELECT * FROM Hero_favorite")
    fun getAllHeroes(): Flow<List<DisneyHeroDbEntity>>

    @Query("SELECT * FROM Hero_favorite WHERE id = :id")
    suspend fun getHeroById(id: Int): DisneyHeroDbEntity?


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHero(hero: DisneyHeroDbEntity)


    @Query("DELETE FROM Hero_favorite WHERE id = :id")
    suspend fun deleteHero(id: Int)
}