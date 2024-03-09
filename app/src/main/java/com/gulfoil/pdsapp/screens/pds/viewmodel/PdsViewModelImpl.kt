package com.gulfoil.pdsapp.screens.pds.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.AdResponse
import com.gulfoil.pdsapp.data.remote.responses.AdResponseItem
import com.gulfoil.pdsapp.domain.MainRepository
import com.gulfoil.pdsapp.utils.isConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class PdsViewModelImpl @Inject constructor(
    private val mainRepository: MainRepository
) : PdsViewModel, ViewModel() {
    override val pdsLiveData = MutableLiveData<InputStream>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val emptyLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val lastLanguageLiveData = MutableLiveData<Languages>()
    override val adResponseLiveData = MutableLiveData<List<AdResponseItem>>()

    override fun getPds(oilId: Int) {
        progressLiveData.value = true
        if (!isConnected()) {
            progressLiveData.value = false
        } else {
            mainRepository.getPDS(oilId).onEach {
                it.onSuccess {
                    if (it.pdf.isNullOrEmpty()) {
                        emptyLiveData.value = true
                        progressLiveData.value = false
                    } else {
                        emptyLiveData.value = false
                        viewModelScope.launch(Dispatchers.IO) {
                            try {
                                val input = URL(it.pdf).openStream()
                                withContext(Dispatchers.Main) {
                                    pdsLiveData.value = input
                                    progressLiveData.value = false
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                errorLiveData.value = e.message
                                progressLiveData.value = false
                            }
                        }
                    }
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