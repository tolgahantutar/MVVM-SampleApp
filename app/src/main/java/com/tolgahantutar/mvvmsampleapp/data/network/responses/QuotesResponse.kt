package com.tolgahantutar.mvvmsampleapp.data.network.responses

import com.tolgahantutar.mvvmsampleapp.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessfull: Boolean,
    val quotes: List<Quote>
)