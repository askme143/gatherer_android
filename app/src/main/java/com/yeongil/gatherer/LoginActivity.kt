package com.yeongil.gatherer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
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
    }

    internal fun replaceFragment(layoutId: Int) {
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

    internal fun moveMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}