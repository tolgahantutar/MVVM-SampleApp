package com.tolgahantutar.mvvmsampleapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

@Entity
data class Quote (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val Id : Int ,
    val quote: String?,
    val author: String?,
    val thumbnail: String?,
    val created_at: String?,
    val updated_at: String?
)