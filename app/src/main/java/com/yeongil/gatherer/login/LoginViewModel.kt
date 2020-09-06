package com.yeongil.gatherer.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = _message

    private val _signUp = MutableLiveData<Event<Boolean>>()
    val signUp: LiveData<Event<Boolean>>
        get() = _signUp

    private val _signIn = MutableLiveData<Event<Boolean>>()
    val signIn: LiveData<Event<Boolean>>
        get() = _signIn

    fun onClickSignIn() {
        val email = email.value
        val password = password.value

        if (email == null || email.isEmpty()) {
            _message.value = Event("이메일을 입력하세요")
        } else if (password == null || password.isEmpty()) {
            _message.value = Event("비밀번호를 입력하세요")
        }
    }

    fun onClickSignUp() {
        val email = email.value
        val password = password.value

        if (email == null || email.isEmpty()) {
            _message.value = Event("이메일을 입력하세요")
        } else if (password == null || password.isEmpty()) {
            _message.value = Event("비밀번호를 입력하세요")
        }
    }

    fun goSignUp() {
        email.value = ""
        password.value = ""
        _signUp.value = Event(true)
    }

    fun goSignIn() {
        email.value = ""
        password.value = ""
        _signIn.value = Event(true)
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