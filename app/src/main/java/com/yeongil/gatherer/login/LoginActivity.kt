package com.yeongil.gatherer.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.yeongil.gatherer.MainActivity
import com.yeongil.gatherer.R

class LoginActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel: LoginViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LoginViewModel::class.java)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SignInFragment()).commit()

        observe(viewModel.message) { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
        observe(viewModel.replaceFragment) { replaceFragment(it) }
        observe(viewModel.goMain) { goMain() }
    }

    private fun <T> observe(liveData: LiveData<Event<T>>, callback: (T) -> Unit) {
        liveData.observe(this, { event ->
            event.getContentIfNotHandled()?.let { callback(it) }
        })
    }

    private fun replaceFragment(layoutId: Int) {
        when (layoutId) {
            R.layout.fragment_sign_in ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SignInFragment()).commit()

            R.layout.fragment_sign_up ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SignUpFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun goMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}