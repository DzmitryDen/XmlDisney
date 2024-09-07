package com.hfad.xmldisney.repository.impl

import com.hfad.xmldisney.database.DisneyHeroDao
import com.hfad.xmldisney.database.entity.DisneyHeroDbEntity
import com.hfad.xmldisney.repository.IntDbRepository
import javax.inject.Inject

class DbRepository @Inject constructor(
    private val dao: DisneyHeroDao
) : IntDbRepository {

    override fun getFavoriteHeroes() = dao.getAllHeroes()

    override suspend fun getHeroById(id: Int) = dao.getHeroById(id)

    override suspend fun addHeroToFavorite(character: DisneyHeroDbEntity) =
        dao.addHero(character)

    override suspend fun deleteHeroFromFavorite(id: Int) = dao.deleteHero(id)
}