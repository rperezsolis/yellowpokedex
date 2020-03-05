package com.rafaelperez.yellowpokedex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private var _signingIn = MutableLiveData<Boolean>()
    val signingIn: LiveData<Boolean>
        get() = _signingIn

    private var _goToMainView = MutableLiveData<Boolean>()
    val goToMainView: LiveData<Boolean>
        get() = _goToMainView

    private var _saveLoggedState = MutableLiveData<Boolean>()
    val saveLoggedState: LiveData<Boolean>
        get() = _saveLoggedState

    private var _userEmail = ""
    private var _password = ""
    private val validEmail = "ashketchum@pokemon.com"
    private val validPassword = "1234"

    init {
        _signingIn.value = false
        _goToMainView.value = false
        _saveLoggedState.value = false
    }

    fun signIn() {
        _signingIn.value = true
    }

    fun setUserCredentials(email: String, password: String) {
        _userEmail = email
        _password = password
        authenticate()
    }

    //todo: save in sharedpreferences a boolean which indicates that the user is already logged
    private fun authenticate() {
        if (_userEmail==validEmail && _password==validPassword) {
            _saveLoggedState.value = true
            _goToMainView.value = true
            resetState()
        }
    }

    private fun resetState() {
        _signingIn.value = false
        _saveLoggedState.value = false
        _goToMainView.value = false
    }
}