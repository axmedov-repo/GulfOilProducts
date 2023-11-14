package com.gulfoil.pdsapp.screens.product.view_model

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages

interface ProductsViewModel {
    val lastLanguageLiveData: LiveData<Languages>

    fun setLanguage(language: Languages)
    fun getLanguage()
}