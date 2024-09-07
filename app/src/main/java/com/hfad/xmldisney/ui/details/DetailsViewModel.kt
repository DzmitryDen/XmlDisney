package com.hfad.xmldisney.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.xmldisney.models.DisneyHero
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.repository.DbRepository
import com.hfad.xmldisney.repository.DisneyRepository
import com.hfad.xmldisney.util.toDisneyHero
import com.hfad.xmldisney.util.toDisneyHeroDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: DisneyRepository,
    private val dbRepository: DbRepository
) : ViewModel() {

    val hero = MutableLiveData<DisneyHero?>(null)
    private var isLiked = MutableLiveData(false)

    fun isSavedHero(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = dbRepository.getHeroById(id)
            if (result != null) {
                hero.postValue(result.toDisneyHero())
            } else {
                getCharacter(id)
            }
        }
    }

    fun likeHerro(id: Int) {
        if (isLiked.value == true) {
            isLiked.value = false
            deleteFromFavorite(id)
        } else {
            isLiked.value = true
            addToFavorite(id)
        }
    }

    private fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCharacterById(id)
            if (response.isSuccessful) {
                response.body()?.toDisneyHero().let {
                    hero.postValue(it)
                }
            } else {
                hero.postValue(
                    DisneyHero(
                        "Noname",
                        "https://static.vecteezy.com/system/resources/previews/026/526/158/original/error-icon-vector.jpg",
                        null
                    )
                )
            }
        }
    }

    private fun addToFavorite(id: Int) {
        hero.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                dbRepository.addHeroToFavorite(
                    DisneyHeroList(
                        id = id,
                        name = it.name,
                        imageUrl = it.image
                    ).toDisneyHeroDbEntity()
                )
            }
        }
    }

    private fun deleteFromFavorite(id: Int) {
        hero.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                dbRepository.deleteHeroFromFavorite(id)
            }
        }
    }
}