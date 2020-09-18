package com.tolgahantutar.mvvmsampleapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.tolgahantutar.mvvmsampleapp.data.repositories.UserRepository
import com.tolgahantutar.mvvmsampleapp.util.ApiException
import com.tolgahantutar.mvvmsampleapp.util.Coroutines
import com.tolgahantutar.mvvmsampleapp.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
): ViewModel() {
    var email: String ? = null //Viewdan alacağımız emaili tutacağımız değişken
    var password : String ? = null// Viewdan alacağımız şifreyi tutacağımız değişken
    var name : String ?= null
    var passwordconfirm: String ?= null

    var authListener: AuthListener?=null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view :View){//View tarafındaki buton tıklanınca yapılacak işlemleri yazacağımız fonksyion.
        //Bir on click fonksiyonu olduğu için içine View'i passlıyoruz.
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            //E-mail veya şifrenin boş olması durumunda yapılacak işler.
            authListener?.onFailure("Invalid email or password")

            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!,password!!)

                authResponse?.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e : NoInternetException){
                authListener?.onFailure(e.message!!)
            }



        }

    }
    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignup(view: View){
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignupButtonClick(view :View){//View tarafındaki buton tıklanınca yapılacak işlemleri yazacağımız fonksyion.
        //Bir on click fonksiyonu olduğu için içine View'i passlıyoruz.
        authListener?.onStarted()
        if(name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }
        if(email.isNullOrEmpty()){
            authListener?.onFailure("Email is required")
        }
        if(password.isNullOrEmpty()){
            authListener?.onFailure("Password is required")
        }
        if(password != passwordconfirm){
            authListener?.onFailure("Password did not match")
        }

        Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!, email!!,password!!)

                authResponse?.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e : NoInternetException){
                authListener?.onFailure(e.message!!)
            }



        }

    }

}