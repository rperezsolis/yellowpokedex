package com.rafaelperez.yellowpokedex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _logged = MutableLiveData<Boolean>()
    val logged: LiveData<Boolean>
        get() = _logged

    init {
        _logged.value = false
    }

    fun setLoggedValue(value: Boolean) {
        _logged.value = value
    }

    fun logout() {
        setLoggedValue(false)
    }
}