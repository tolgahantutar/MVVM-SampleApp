package com.tolgahantutar.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tolgahantutar.mvvmsampleapp.data.db.entities.CURRENT_USER_ID
import com.tolgahantutar.mvvmsampleapp.data.db.entities.User


@Dao
interface UserDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)//onConflict metodu karışıklık çıkması durumunda (örneğin birden fazla p.k
    // gilirse veya aynı kayıt tekrar eklenirse ) kullanılır.
    suspend fun upsert(user:User): Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser(): LiveData<User>

}