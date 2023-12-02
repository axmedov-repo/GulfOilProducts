package com.gulfoil.pdsapp.screens.product.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.AdResponse
import com.gulfoil.pdsapp.data.remote.responses.ProductResponse
import com.gulfoil.pdsapp.domain.MainRepository
import com.gulfoil.pdsapp.utils.isConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsViewModelImpl @Inject constructor(
    private val mainRepository: MainRepository
) : ProductsViewModel, ViewModel() {
    override val productsLiveData = MutableLiveData<ProductResponse>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val adResponseLiveData = MutableLiveData<AdResponse>()

    override fun getProducts() {
        progressLiveData.value = true
        if (!isConnected()) {
            progressLiveData.value = false
        } else {
            mainRepository.getProducts().onEach {
                it.onSuccess {
                    productsLiveData.value = it
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

    override val lastLanguageLiveData = MutableLiveData<Languages>()

    override fun setLanguage(language: Languages) {
        mainRepository.setLanguage(language)
    }

    override fun getLanguage() {
        lastLanguageLiveData.value = mainRepository.getLanguage()
    }
}