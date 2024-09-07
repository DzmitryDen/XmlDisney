package com.hfad.xmldisney.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.xmldisney.repository.DbRepository
import com.hfad.xmldisney.repository.DisneyRepository
import javax.inject.Inject

class HomeVMProvider @Inject constructor(
    private val repository: DisneyRepository,
    private val dbRepository: DbRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository, dbRepository) as T
    }
}