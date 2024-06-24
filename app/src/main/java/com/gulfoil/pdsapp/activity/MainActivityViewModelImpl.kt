package com.gulfoil.pdsapp.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.domain.main.MainRepository
import com.gulfoil.pdsapp.utils.connection.checkInternetConnection
import com.gulfoil.pdsapp.utils.connection.setInternetConnectionListener
import com.gulfoil.pdsapp.utils.setShowMessageOnTopOfScreenListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

@HiltViewModel
class MainActivityViewModelImpl @Inject constructor(
    private val repository: MainRepository
) : MainActivityViewModel, ViewModel() {
    override val internetConnectionLiveData = MutableLiveData<Boolean>()
    override val showMessageOnTopOfScreenLiveData = MutableLiveData<String>()

    init {
        checkInternetConnection()
        setInternetConnectionListener { isInternetConnected ->
            internetConnectionLiveData.value = isInternetConnected
        }
        setShowMessageOnTopOfScreenListener { message ->
            showMessageOnTopOfScreenLiveData.value = message
        }
    }

    override val appLanguage: Languages = repository.getLanguage()
}
