package com.yeongil.gatherer.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yeongil.gatherer.databinding.SignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding: SignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignUpBinding.inflate(inflater, container, false)
        binding.vm = ViewModelProvider(
            activity as LoginActivity,
            ViewModelProvider.NewInstanceFactory()
        ).get(LoginViewModel::class.java)

        return binding.root
    }
}