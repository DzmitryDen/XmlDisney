package com.hfad.xmldisney.repository

import com.hfad.xmldisney.database.DisneyHeroDao
import com.hfad.xmldisney.database.entity.DisneyHeroDbEntity
import javax.inject.Inject

class DbRepository @Inject constructor(
    private val dao: DisneyHeroDao
) {

    fun getFavoriteHeroes() = dao.getAllHeroes()

    suspend fun getHeroById(id: Int) = dao.getHeroById(id)

    suspend fun addHeroToFavorite(character: DisneyHeroDbEntity) =
        dao.addHero(character)

    suspend fun deleteHeroFromFavorite(id: Int) = dao.deleteHero(id)
}