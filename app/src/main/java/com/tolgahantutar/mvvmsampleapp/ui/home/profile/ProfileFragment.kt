package com.tolgahantutar.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tolgahantutar.mvvmsampleapp.R
import com.tolgahantutar.mvvmsampleapp.databinding.ProfileFragmentBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(),KodeinAware {

override val kodein by kodein()
    private val factory: ProfileViewModelFactory by instance<ProfileViewModelFactory>()
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment,container,false)
        viewModel = ViewModelProviders.of(this,factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }



}