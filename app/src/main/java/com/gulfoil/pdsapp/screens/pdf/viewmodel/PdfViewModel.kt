package com.gulfoil.pdsapp.screens.pdf.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages

interface PdfViewModel {
    val lastLanguageLiveData: LiveData<Languages>

    fun setLanguage(language: Languages)
    fun getLanguage()
}