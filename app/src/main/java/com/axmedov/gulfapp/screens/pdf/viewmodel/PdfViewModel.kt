package com.axmedov.gulfapp.screens.pdf.viewmodel

import androidx.lifecycle.LiveData
import com.axmedov.gulfapp.data.enums.Languages

interface PdfViewModel {
    val lastLanguageLiveData: LiveData<Languages>

    fun setLanguage(language: Languages)
    fun getLanguage()
}