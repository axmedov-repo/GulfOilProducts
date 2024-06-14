package com.gulfoil.pdsapp.screens.pds.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.product.AdResponseItem
import java.io.InputStream
import java.net.URL

interface PdsViewModel {
    val pdsLiveData: LiveData<InputStream>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val emptyLiveData: LiveData<Boolean>
    val lastLanguageLiveData: LiveData<Languages>
    val adResponseLiveData: LiveData<List<AdResponseItem>>

    fun getPds(oilId: Int)
    fun setLanguage(language: Languages)
    fun getLanguage()
    fun getAds()
}
