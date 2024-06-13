package com.gulfoil.pdsapp.screens.oils.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.product.AdResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.OilResponseItem
import com.gulfoil.pdsapp.domain.main.MainRepository
import com.gulfoil.pdsapp.utils.isConnected
import com.gulfoil.pdsapp.utils.timber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OilsViewModelImpl @Inject constructor(
    private val mainRepository: MainRepository
) : OilsViewModel, ViewModel() {
    override val oilsLiveData = MutableLiveData<List<OilResponseItem>>()
    override val searchResponseLiveData = MutableLiveData<List<OilResponseItem>>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val lastLanguageLiveData = MutableLiveData<Languages>()
    override val adResponseLiveData = MutableLiveData<List<AdResponseItem>>()

    override fun getOils(productId: Int) {
        progressLiveData.value = true
        if (!isConnected()) {
            progressLiveData.value = false
        } else {
            mainRepository.getOils(productId).onEach {
                it.onSuccess {
                    oilsLiveData.value = it
                    progressLiveData.value = false
                }
                it.onFailure {
                    errorLiveData.value = it.message
                    progressLiveData.value = false
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun searchOil(productId: Int, query: String) {
        progressLiveData.value = true
        if (!isConnected()) {
            progressLiveData.value = false
        } else {
            mainRepository.getOils(productId, query).onEach {
                timber("query=$query, list=$it", "OIL_LOGS")
                it.onSuccess {
                    searchResponseLiveData.value = it
                    progressLiveData.value = false
                }
                it.onFailure {
                    errorLiveData.value = it.message
                    progressLiveData.value = false
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun getAds() {
        progressLiveData.value = true
        if (!isConnected()) {
            progressLiveData.value = false
        } else {
            mainRepository.getAds().onEach {
                it.onSuccess {
                    adResponseLiveData.value = it
                    progressLiveData.value = false
                }
                it.onFailure {
                    errorLiveData.value = it.message
                    progressLiveData.value = false
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun setLanguage(language: Languages) {
        mainRepository.setLanguage(language)
    }

    override fun getLanguage() {
        lastLanguageLiveData.value = mainRepository.getLanguage()
    }
}