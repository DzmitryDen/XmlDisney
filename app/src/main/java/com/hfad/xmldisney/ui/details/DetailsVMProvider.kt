package com.hfad.xmldisney.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.xmldisney.repository.IntDbRepository
import com.hfad.xmldisney.repository.IntDisneyRepository
import javax.inject.Inject

class DetailsVMProvider @Inject constructor(
    private val repository: IntDisneyRepository,
    private val dbRepository: IntDbRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(repository, dbRepository) as T
    }
}