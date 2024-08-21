package com.hfad.xmldisney.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.repository.DbRepository
import com.hfad.xmldisney.repository.DisneyRepository
import com.hfad.xmldisney.util.toDisneyHeroList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DisneyRepository,
    private val dbRepository: DbRepository
) : ViewModel() {

    val disneyHeroes = MutableLiveData<List<DisneyHeroList>?>()

    fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getListCharacters()
            if (response.isSuccessful) {
                response.body()?.toDisneyHeroList().let {
                    disneyHeroes.postValue(it)
                }
            } else { //Не работает апи
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