package com.hfad.xmldisney.repository

import com.hfad.xmldisney.network.DisneyApi
import javax.inject.Inject

class DisneyRepository @Inject constructor(
    private val api: DisneyApi
) {

    suspend fun getListCharacters() = api.getCharacters()

    suspend fun getCharacterById(id: Int) = api.getCharacterById(id)
}