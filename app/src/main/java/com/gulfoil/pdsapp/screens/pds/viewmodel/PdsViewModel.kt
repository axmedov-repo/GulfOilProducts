package com.gulfoil.pdsapp.screens.pds.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.PdsResponse

interface PdsViewModel {
    val pdsLiveData: LiveData<PdsResponse>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val lastLanguageLiveData: LiveData<Languages>

    fun getPds(oilId : Int)
    fun setLanguage(language: Languages)
    fun getLanguage()
}