package com.hfad.xmldisney.repository

import com.hfad.xmldisney.database.entity.DisneyHeroDbEntity
import kotlinx.coroutines.flow.Flow

interface IntDbRepository {

    fun getFavoriteHeroes(): Flow<List<DisneyHeroDbEntity>>

    suspend fun getHeroById(id: Int): DisneyHeroDbEntity?

    suspend fun addHeroToFavorite(character: DisneyHeroDbEntity)

    suspend fun deleteHeroFromFavorite(id: Int)
}