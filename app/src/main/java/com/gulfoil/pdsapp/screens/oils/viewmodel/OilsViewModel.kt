package com.gulfoil.pdsapp.screens.oils.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.OilResponse

interface OilsViewModel {
    val oilsLiveData: LiveData<OilResponse>
    val searchResponseLiveData: LiveData<OilResponse>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val lastLanguageLiveData: LiveData<Languages>

    fun getOils(productId: Int)
    fun searchOil(productId: Int, query: String)
    fun setLanguage(language: Languages)
    fun getLanguage()
}