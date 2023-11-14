package com.gulfoil.pdsapp.screens.oils.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages

interface OilsViewModel {
    val lastLanguageLiveData: LiveData<Languages>

    fun setLanguage(language: Languages)
    fun getLanguage()
}