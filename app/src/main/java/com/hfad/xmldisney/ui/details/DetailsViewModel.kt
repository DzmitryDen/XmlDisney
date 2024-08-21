package com.hfad.xmldisney.ui.details

import androidx.lifecycle.ViewModel
import com.hfad.xmldisney.models.DisneyHero
import com.hfad.xmldisney.repository.HeroResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: GetHeroByIdUseCase
) : ViewModel() {

    val hero = PublishSubject.create<DisneyHero?>()
    var showError: ((throwable: Throwable) -> Unit)? = null
    private val disposable = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun getCharacter(id: Int) {
        disposable.add(
            useCase.loadCharacterById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    handleResult(result)
                }, { error ->
                    handleResult(HeroResult.Error(error))
                })
        )
    }

    private fun handleResult(result: HeroResult) {
        when (result) {
            is HeroResult.Success<*> -> {
                hero.onNext(result.data as DisneyHero)
            }

            is HeroResult.Error -> {
                result.throwable.let { showError?.invoke(it) }

            }
        }
    }
}