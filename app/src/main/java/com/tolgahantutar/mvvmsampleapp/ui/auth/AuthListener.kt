package com.tolgahantutar.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import com.tolgahantutar.mvvmsampleapp.data.db.entities.User

interface AuthListener {//Viewmodeldan viewa callback yapma amaçlı kullanacağımız interface
    fun onStarted()// Bir network işlemi yapılacağından bu zaman alacak ve onStarted'da bir progress bar göstereceğiz.
    fun onSuccess(user:User)
    fun onFailure(message:String)

}