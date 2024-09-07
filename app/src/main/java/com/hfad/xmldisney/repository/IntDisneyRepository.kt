package com.hfad.xmldisney.repository

import com.hfad.xmldisney.network.entity.DisneyHeroResponse
import com.hfad.xmldisney.network.entity.ListDisneyHeroResponse
import retrofit2.Response

interface IntDisneyRepository {

    suspend fun getListCharacters(): Response<ListDisneyHeroResponse>

    suspend fun getCharacterById(id: Int): Response<DisneyHeroResponse>
}