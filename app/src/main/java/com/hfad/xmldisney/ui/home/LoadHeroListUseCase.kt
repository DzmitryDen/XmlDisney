package com.hfad.xmldisney.ui.home

import com.hfad.xmldisney.repository.DisneyRepository
import com.hfad.xmldisney.repository.HeroResult
import com.hfad.xmldisney.util.toDisneyHeroList
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class LoadHeroListUseCase @Inject constructor(private val reposit: DisneyRepository) {

    fun loadListHero(): Observable<HeroResult> {
        return reposit.getListCharacters().map { result ->
            if (result.isSuccessful) {
                HeroResult.Success(result.body()?.toDisneyHeroList() ?: arrayListOf())
            } else {
                HeroResult.Error(Throwable(result.message()))
            }
        }
    }
}