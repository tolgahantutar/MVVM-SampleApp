package com.tolgahantutar.mvvmsampleapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolgahantutar.mvvmsampleapp.data.db.entities.Quote

/**
 *
 * KOTLIN COROUTINE ILE Room database methodlarına suspend ekleyerek asynchronize calısmasını sağlayabilirsiniz.
 *
 */
@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(quotes: List<Quote>)

    @Query("SELECT * FROM Quote")
    suspend fun getQuotes(): List<Quote>
}