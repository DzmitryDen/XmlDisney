package com.hfad.xmldisney.network

import com.hfad.xmldisney.network.entity.DisneyHeroResponse
import com.hfad.xmldisney.network.entity.ListDisneyHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DisneyApi {

    @GET("character")
    suspend fun getCharacters() : Response<ListDisneyHeroResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<DisneyHeroResponse>
}