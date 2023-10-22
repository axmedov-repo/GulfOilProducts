package com.axmedov.gulfapp.screens.product.view_model

import androidx.lifecycle.LiveData
import com.axmedov.gulfapp.data.enums.Languages

interface ProductsViewModel {
    val lastLanguageLiveData: LiveData<Languages>

    fun setLanguage(language: Languages)
    fun getLanguage()
}