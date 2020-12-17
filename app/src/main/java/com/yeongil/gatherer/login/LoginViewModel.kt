package com.yeongil.gatherer.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.yeongil.gatherer.R

class LoginViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = _message

    private val _replaceFragment = MutableLiveData<Event<Int>>()
    val replaceFragment: LiveData<Event<Int>>
        get() = _replaceFragment

    private val _goMain = MutableLiveData<Event<Boolean>>()
    val goMain: LiveData<Event<Boolean>>
        get() = _goMain

    fun onClickSignIn() {
        val email = email.value
        val password = password.value

        if (email == null || email.isEmpty()) {
            showMessage("이메일을 입력하세요")
            return
        } else if (password == null || password.isEmpty()) {
            showMessage("비밀번호를 입력하세요")
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    goMain()
                } else {
                    showMessage(task.exception?.message!!)
                }
            }
    }

    fun onClickSignUp() {
        val email = email.value
        val password = password.value

        if (email == null || email.isEmpty()) {
            showMessage("이메일을 입력하세요")
            return
        } else if (password == null || password.isEmpty()) {
            showMessage("비밀번호를 입력하세요")
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    goMain()
                } else {
                    showMessage(task.exception?.message!!)
                }
            }
    }

    fun goSignUp() {
        email.value = ""
        password.value = ""
        _replaceFragment.value = Event(R.layout.fragment_sign_up)
    }

    fun goSignIn() {
        email.value = ""
        password.value = ""
        _replaceFragment.value = Event(R.layout.fragment_sign_in)
    }

    private fun goMain() {
        email.value = ""
        password.value = ""
        _goMain.value = Event(true)
    }

    private fun showMessage(message: String){
        _message.value = Event(message)
    }
}

class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        if (hasBeenHandled) return null

        hasBeenHandled = true

        return content
    }

    fun peekContent(): T = content
}