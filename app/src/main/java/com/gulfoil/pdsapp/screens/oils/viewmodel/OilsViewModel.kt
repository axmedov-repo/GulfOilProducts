package com.gulfoil.pdsapp.screens.oils.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.product.AdResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.OilResponseItem

interface OilsViewModel {
    val oilsLiveData: LiveData<List<OilResponseItem>>
    val searchResponseLiveData: LiveData<List<OilResponseItem>>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val lastLanguageLiveData: LiveData<Languages>
    val adResponseLiveData: LiveData<List<AdResponseItem>>

    fun getOils(productId: Int)
    fun searchOil(productId: Int, query: String)
    fun setLanguage(language: Languages)
    fun getLanguage()
    fun getAds()
}