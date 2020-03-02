package com.rafaelperez.yellowpokedex.viewmodels

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _logged: Boolean
    val logged: Boolean
        get() = _logged

    init {
        _logged = false
    }

    fun setLoggedValue(value: Boolean) {
        _logged = value
    }
}