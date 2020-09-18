package com.tolgahantutar.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.tolgahantutar.mvvmsampleapp.data.repositories.QuotesRepository
import com.tolgahantutar.mvvmsampleapp.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {
    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}