package com.tolgahantutar.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgahantutar.mvvmsampleapp.data.db.entities.Quote
import com.tolgahantutar.mvvmsampleapp.data.repositories.QuotesRepository
import com.tolgahantutar.mvvmsampleapp.util.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface IQuotesViewModel {
    fun loadQuotes()
}

class QuotesViewModel(
    private val repository: QuotesRepository
) : ViewModel(), IQuotesViewModel {

    private val _quotesLiveData: MutableLiveData<List<Quote>> = MutableLiveData(listOf())

    fun successLoadedQuotes(): LiveData<List<Quote>> = _quotesLiveData

    override fun loadQuotes() {
        viewModelScope.launch {
            var result: List<Quote> = listOf()
            withContext(Dispatchers.IO) {
                result = repository.getQuotes()
            }
            _quotesLiveData.postValue(result)
        }
    }
}