package com.tolgahantutar.mvvmsampleapp.data.repositories

import android.util.Log
import com.tolgahantutar.mvvmsampleapp.data.db.AppDatabase
import com.tolgahantutar.mvvmsampleapp.data.db.entities.Quote
import com.tolgahantutar.mvvmsampleapp.data.network.MyApi
import com.tolgahantutar.mvvmsampleapp.data.network.SafeApiRequest
import com.tolgahantutar.mvvmsampleapp.data.preferences.PreferenceProvider
import java.time.LocalDateTime

private val MINIMUM_INTERVAL = 6

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
): SafeApiRequest() {

    suspend fun getQuotes(): List<Quote> {
        fetchQuotes()
        val list = db.getQuoteDao().getQuotes()
        Log.i("QuotesRepository", "Quotes count is ${list.size}")
        return list
    }
    private suspend fun fetchQuotes(){
        val lastSavedAt = prefs.getLastSavedAt()
        if(isFetchNeeded()){
            val response = apiRequest { api.getQuotes() }
            saveQuotes(response.quotes)
        }
    }
    private fun isFetchNeeded(): Boolean{
        return true
    }
    private suspend fun saveQuotes(quotes: List<Quote>){
        prefs.savelastSavedAt(LocalDateTime.now().toString())
        db.getQuoteDao().saveAllQuotes(quotes)
    }
}