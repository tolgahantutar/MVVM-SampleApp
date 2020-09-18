package com.tolgahantutar.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.tolgahantutar.mvvmsampleapp.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {
    val user = repository.getUser()
}