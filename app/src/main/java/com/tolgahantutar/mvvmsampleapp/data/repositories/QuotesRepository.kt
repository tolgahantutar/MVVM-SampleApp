package com.tolgahantutar.mvvmsampleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tolgahantutar.mvvmsampleapp.data.db.AppDatabase
import com.tolgahantutar.mvvmsampleapp.data.db.entities.Quote
import com.tolgahantutar.mvvmsampleapp.data.network.MyApi
import com.tolgahantutar.mvvmsampleapp.data.network.SafeApiRequest
import com.tolgahantutar.mvvmsampleapp.data.preferences.PreferenceProvider
import com.tolgahantutar.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
): SafeApiRequest() {

    private val quotes  = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }
    suspend fun getQuotes(): LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }
    private suspend fun fetchQuotes(){
        val lastSavedAt = prefs.getLastSavedAt()
        if(isFetchNeeded()){
        val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }
    private fun isFetchNeeded(): Boolean{

        return true
    }
    private fun saveQuotes(quotes: List<Quote>){
        Coroutines.io {
            prefs.savelastSavedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}