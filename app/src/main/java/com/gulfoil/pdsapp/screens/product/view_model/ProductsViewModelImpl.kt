package com.gulfoil.pdsapp.screens.product.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.enums.Languages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModelImpl @Inject constructor(
    private val localStorage: LocalStorage
) : ProductsViewModel, ViewModel() {

    override val lastLanguageLiveData = MutableLiveData<Languages>()

    override fun setLanguage(language: Languages) {
        localStorage.appLanguage = language
    }

    override fun getLanguage() {
        lastLanguageLiveData.value = localStorage.appLanguage
    }
}