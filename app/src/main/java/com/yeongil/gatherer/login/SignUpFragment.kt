package com.yeongil.gatherer.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yeongil.gatherer.LoginActivity
import com.yeongil.gatherer.R
import com.yeongil.gatherer.databinding.SignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding: SignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: LoginViewModel = ViewModelProvider(
            activity as LoginActivity,
            ViewModelProvider.NewInstanceFactory()
        ).get(LoginViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.vm = viewModel

        return binding.root
    }
}