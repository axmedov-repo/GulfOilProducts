package com.gulfoil.pdsapp.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.enums.MessageType
import com.gulfoil.pdsapp.domain.main.MainRepository
import com.gulfoil.pdsapp.utils.connection.checkInternetConnection
import com.gulfoil.pdsapp.utils.connection.setInternetConnectionListener
import com.gulfoil.pdsapp.utils.setShowMessageListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

@HiltViewModel
class MainActivityViewModelImpl @Inject constructor(
    repository: MainRepository
) : MainActivityViewModel, ViewModel() {
    override val internetConnectionLiveData = MutableLiveData<Boolean>()
    override val showMessageLiveData = MutableLiveData<Triple<String, MessageType, () -> Unit>>()

    init {
        checkInternetConnection()
        setInternetConnectionListener { isInternetConnected ->
            internetConnectionLiveData.value = isInternetConnected
        }
        setShowMessageListener { message, messageType, returnAction ->
            showMessageLiveData.value = Triple(message, messageType, returnAction)
        }
    }

    override val appLanguage: Languages = repository.getLanguage()
}
