package com.gulfoil.pdsapp.screens.trial_end

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages

interface TrialEndViewModel {
    val appLanguage: LiveData<Languages>
    fun getAppLanguage()
}