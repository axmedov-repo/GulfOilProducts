package com.gulfoil.pdsapp.screens.splash

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val successLiveData: LiveData<Unit>

    fun exchangeEncryptionDetails()
}