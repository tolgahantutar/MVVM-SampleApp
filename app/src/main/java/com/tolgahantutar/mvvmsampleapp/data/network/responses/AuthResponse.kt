package com.tolgahantutar.mvvmsampleapp.data.network.responses

import com.tolgahantutar.mvvmsampleapp.data.db.entities.User

data class AuthResponse(
    val isSuccessfull : Boolean?,
    val message: String?,
    val user: User?


)