package com.hfad.xmldisney.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.repository.HeroResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: LoadHeroListUseCase
) : ViewModel() {

    val disneyHeroes = MutableLiveData<List<DisneyHeroList>>()
    var showError: ((throwable: Throwable) -> Unit)? = null
    private val disposable = CompositeDisposable()

    fun loadListData() {
        disposable.add(
            useCase.loadListHero()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe({ result ->
                    handleResult(result)
                }, { error ->
                    handleResult(HeroResult.Error(error))
                }
                )
        )
    }

    private fun handleResult(result: HeroResult) {
        when (result) {
            is HeroResult.Success<*> -> {
                disneyHeroes.value = result.data as List<DisneyHeroList>

            }

            is HeroResult.Error -> {
                result.throwable.let { showError?.invoke(it) }

            }
        }
    }
}