package com.hfad.xmldisney.network

import com.hfad.xmldisney.network.entity.DisneyHeroResponse
import com.hfad.xmldisney.network.entity.ListDisneyHeroResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DisneyApi {

    @GET("character")
    fun getCharacters(): Observable<Response<ListDisneyHeroResponse>>

    @GET("character/{id}")
    fun getCharacterById(
        @Path("id") id: Int
    ): Single<Response<DisneyHeroResponse>>
}