package com.hfad.xmldisney.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.xmldisney.models.DisneyHero
import com.hfad.xmldisney.repository.DisneyRepository
import com.hfad.xmldisney.util.toDisneyHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DisneyRepository
) : ViewModel() {

    val hero = MutableLiveData<DisneyHero?>(null)

    fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCharacterById(id)
            if (response.isSuccessful) {
                response.body()?.toDisneyHero().let {
                    hero.postValue(it)
                }
            }
        }
    }
}