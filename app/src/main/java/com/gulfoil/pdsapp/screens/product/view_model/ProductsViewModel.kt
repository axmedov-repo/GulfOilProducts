package com.gulfoil.pdsapp.screens.product.view_model

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.AdResponse
import com.gulfoil.pdsapp.data.remote.responses.ProductResponse

interface ProductsViewModel {
    val productsLiveData: LiveData<ProductResponse>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val lastLanguageLiveData: LiveData<Languages>
    val adResponseLiveData: LiveData<AdResponse>

    fun getProducts()
    fun setLanguage(language: Languages)
    fun getLanguage()
    fun getAds()
}
