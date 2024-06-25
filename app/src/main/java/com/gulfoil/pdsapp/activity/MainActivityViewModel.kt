package com.gulfoil.pdsapp.activity

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.enums.MessageType

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

interface MainActivityViewModel {
    val internetConnectionLiveData: LiveData<Boolean>
    val showMessageLiveData: LiveData<Triple<String, MessageType, () -> Unit>>

    val appLanguage: Languages
}