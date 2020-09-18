package com.tolgahantutar.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tolgahantutar.mvvmsampleapp.R
import com.tolgahantutar.mvvmsampleapp.data.db.entities.User
import com.tolgahantutar.mvvmsampleapp.databinding.ActivityLoginBinding
import com.tolgahantutar.mvvmsampleapp.databinding.ActivitySignUpBinding
import com.tolgahantutar.mvvmsampleapp.ui.home.HomeActivity
import com.tolgahantutar.mvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(),AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance<AuthViewModelFactory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySignUpBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.visibility= View.VISIBLE
        toast("Signin Up..")
    }

    override fun onSuccess(user: User) {
        progress_bar.visibility = View.INVISIBLE

    }

    override fun onFailure(message: String) {
        progress_bar.visibility = View.INVISIBLE
        toast(message)
    }
}