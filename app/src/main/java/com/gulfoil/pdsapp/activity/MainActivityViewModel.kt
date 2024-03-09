package com.gulfoil.pdsapp.activity

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

interface MainActivityViewModel {
    val internetConnectionLiveData: LiveData<Boolean>
    val showMessageOnTopOfScreenLiveData: LiveData<String>

    val appLanguage: Languages
}