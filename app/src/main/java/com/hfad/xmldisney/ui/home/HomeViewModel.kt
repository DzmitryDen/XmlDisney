package com.hfad.xmldisney.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.repository.IntDbRepository
import com.hfad.xmldisney.repository.IntDisneyRepository
import com.hfad.xmldisney.util.toDisneyHeroList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: IntDisneyRepository,
    private val dbRepository: IntDbRepository
) : ViewModel() {

    val disneyHeroes = MutableLiveData<List<DisneyHeroList>?>()
    val error = MutableLiveData<String?>(null)

    fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getListCharacters()
            if (response.isSuccessful) {
                response.body()?.toDisneyHeroList().let {
                    disneyHeroes.postValue(it)
                }
            } else { //Не работает апи
                error.postValue(response.message())
                disneyHeroes.postValue(
                    arrayListOf(
                        DisneyHeroList(
                            1,
                            "Error",
                            "https://static.vecteezy.com/system/resources/previews/026/526/158/original/error-icon-vector.jpg"
                        ),
                        DisneyHeroList(
                            2,
                            "Error",
                            "https://static.vecteezy.com/system/resources/previews/026/526/158/original/error-icon-vector.jpg"
                        ),
                    )
                )
            }
        }
    }

    fun showAll() {
        loadListData()
    }

    fun showFavorite() {
        loadFavoriteHero()
    }

    private fun loadFavoriteHero() {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepository.getFavoriteHeroes().collect { result ->
                if (result.toDisneyHeroList().isNotEmpty()) {
                    disneyHeroes.postValue(result.toDisneyHeroList())
                } else {
                    disneyHeroes.postValue(null)
                }
            }
        }
    }
}