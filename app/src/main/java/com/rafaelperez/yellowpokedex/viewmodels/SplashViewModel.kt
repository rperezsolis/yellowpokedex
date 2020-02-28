package com.rafaelperez.yellowpokedex.viewmodels

import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    //todo: this variable is going to be fetched from SharedPreferences
    private var _logged: Boolean = false
        val logged: Boolean
        get() = _logged
}