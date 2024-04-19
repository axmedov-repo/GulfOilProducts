package com.gulfoil.pdsapp.screens.product.view_model

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.product.AdResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.ProductResponseItem

interface ProductsViewModel {
    val productsLiveData: LiveData<List<ProductResponseItem>>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val lastLanguageLiveData: LiveData<Languages>
    val adResponseLiveData: LiveData<List<AdResponseItem>>

    fun getProducts()
    fun setLanguage(language: Languages)
    fun getLanguage()
    fun getAds()
}
