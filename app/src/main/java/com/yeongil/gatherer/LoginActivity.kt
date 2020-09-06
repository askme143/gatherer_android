package com.yeongil.gatherer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yeongil.gatherer.login.Event
import com.yeongil.gatherer.login.LoginViewModel
import com.yeongil.gatherer.login.SignInFragment
import com.yeongil.gatherer.login.SignUpFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : FragmentActivity() {
    private val mSignInFragment = SignInFragment()
    private val mSignUpFragment = SignUpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val viewModel: LoginViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LoginViewModel::class.java)

        if (fragment_container != null) {
            if (savedInstanceState != null) return

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, mSignInFragment).commit()
        }

        observe(viewModel.message) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
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
                    .replace(R.id.fragment_container, mSignInFragment).commit()

            R.layout.fragment_sign_up ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, mSignUpFragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun goMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}