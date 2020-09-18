package com.tolgahantutar.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tolgahantutar.mvvmsampleapp.data.repositories.QuotesRepository
import com.tolgahantutar.mvvmsampleapp.data.repositories.UserRepository

@Suppress("UNCHECKED_CAS T")
class QuotesViewModelFactory (
    private val repository: QuotesRepository
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}