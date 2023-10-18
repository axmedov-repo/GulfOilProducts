package com.axmedov.gulfapp.screens.pdf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axmedov.gulfapp.data.cache.LanguagePreference
import com.axmedov.gulfapp.data.enums.Languages

//@HiltViewModel
class PdfViewModelImpl : PdfViewModel, ViewModel() {
    private val languagePreference = LanguagePreference.getInstance()

    override val lastLanguageLiveData = MutableLiveData<Languages>()

    override fun setLanguage(language: Languages) {
        languagePreference.appLanguage = language
    }

    override fun getLanguage() {
        lastLanguageLiveData.value = languagePreference.appLanguage
    }
}