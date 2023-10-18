package com.axmedov.gulfapp.screens.new_variant.viewmodel

import androidx.lifecycle.LiveData
import com.axmedov.gulfapp.data.enums.Languages

interface OilsViewModel {
    val lastLanguageLiveData: LiveData<Languages>

    fun setLanguage(language: Languages)
    fun getLanguage()
}