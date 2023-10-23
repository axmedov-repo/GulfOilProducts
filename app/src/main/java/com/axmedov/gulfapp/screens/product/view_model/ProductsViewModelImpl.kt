package com.axmedov.gulfapp.screens.product.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axmedov.gulfapp.data.cache.LocalStorage
import com.axmedov.gulfapp.data.enums.Languages

//@HiltViewModel
class ProductsViewModelImpl : ProductsViewModel, ViewModel() {
    private val localStorage = LocalStorage.getInstance()
    override val lastLanguageLiveData = MutableLiveData<Languages>()

    override fun setLanguage(language: Languages) {
        localStorage.appLanguage = language
    }

    override fun getLanguage() {
        lastLanguageLiveData.value = localStorage.appLanguage
    }
}