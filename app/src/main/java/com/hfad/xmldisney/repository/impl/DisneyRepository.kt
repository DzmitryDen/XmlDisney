package com.hfad.xmldisney.repository.impl

import com.hfad.xmldisney.network.DisneyApi
import com.hfad.xmldisney.repository.IntDisneyRepository
import javax.inject.Inject

class DisneyRepository @Inject constructor(
    private val api: DisneyApi
) : IntDisneyRepository {

    override suspend fun getListCharacters() = api.getCharacters()

    override suspend fun getCharacterById(id: Int) = api.getCharacterById(id)
}