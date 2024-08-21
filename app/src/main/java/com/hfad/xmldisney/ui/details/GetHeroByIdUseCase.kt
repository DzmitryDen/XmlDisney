package com.hfad.xmldisney.ui.details

import com.hfad.xmldisney.repository.DisneyRepository
import com.hfad.xmldisney.repository.HeroResult
import com.hfad.xmldisney.util.toDisneyHero
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetHeroByIdUseCase @Inject constructor(private val reposit: DisneyRepository) {

    fun loadCharacterById(id: Int): Single<HeroResult> {
        return reposit.getCharacterById(id).map { result ->
            if (result.isSuccessful) {
                HeroResult.Success(result.body()?.toDisneyHero())
            } else {
                HeroResult.Error(Throwable(result.message()))
            }
        }
    }
}