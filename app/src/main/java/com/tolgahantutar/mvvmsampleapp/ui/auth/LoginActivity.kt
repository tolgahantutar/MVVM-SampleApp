package com.tolgahantutar.mvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.tolgahantutar.mvvmsampleapp.R
import com.tolgahantutar.mvvmsampleapp.data.db.entities.User
import com.tolgahantutar.mvvmsampleapp.databinding.ActivityLoginBinding
import com.tolgahantutar.mvvmsampleapp.ui.home.HomeActivity
import com.tolgahantutar.mvvmsampleapp.util.ApiException
import com.tolgahantutar.mvvmsampleapp.util.NoInternetException
import com.tolgahantutar.mvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance<AuthViewModelFactory>()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)


        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null){
                Intent(this,HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
        binding.buttonSignIn.setOnClickListener{
            loginUser()
        }
        binding.textViewSignUp.setOnClickListener {
           startActivity(Intent(this,SignUpActivity::class.java))
        }
    }
    private fun loginUser(){
    val email = binding.editTextEmail.text.toString().trim()
    val password = binding.editTextPassword.text.toString().trim()

        lifecycleScope.launch {
            try {
                val authResponse = viewModel.userLogin(email,password)
                if(authResponse.user!=null){
                authResponse?.user?.let {

                    viewModel.saveLoggedInUser(authResponse.user)

                }

                } else{
                    toast(authResponse.message!!)
                }
            }catch (e: ApiException){
                e.printStackTrace()
            }catch (e : NoInternetException){
                e.printStackTrace()
            }
        }

    }


}