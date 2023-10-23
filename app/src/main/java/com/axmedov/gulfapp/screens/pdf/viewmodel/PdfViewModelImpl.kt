package com.axmedov.gulfapp.screens.pdf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axmedov.gulfapp.data.cache.LocalStorage
import com.axmedov.gulfapp.data.enums.Languages

//@HiltViewModel
class PdfViewModelImpl : PdfViewModel, ViewModel() {
    private val localStorage = LocalStorage.getInstance()

    override val lastLanguageLiveData = MutableLiveData<Languages>()

    override fun setLanguage(language: Languages) {
        localStorage.appLanguage = language
    }

    override fun getLanguage() {
        lastLanguageLiveData.value = localStorage.appLanguage
    }
}