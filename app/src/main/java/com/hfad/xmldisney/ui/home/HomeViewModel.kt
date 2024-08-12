package com.hfad.xmldisney.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.repository.DisneyRepository
import com.hfad.xmldisney.util.toDisneyHeroList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DisneyRepository
) : ViewModel() {

    val disneyHeroes = MutableLiveData<List<DisneyHeroList>>()

    fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getListCharacters()
            if (response.isSuccessful) {
                response.body()?.toDisneyHeroList().let {
                    disneyHeroes.postValue(it)
                }
            }
        }
    }
}