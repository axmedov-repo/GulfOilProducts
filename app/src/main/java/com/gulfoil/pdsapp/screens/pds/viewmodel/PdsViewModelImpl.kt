package com.gulfoil.pdsapp.screens.pds.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.PdsResponse
import com.gulfoil.pdsapp.domain.MainRepository
import com.gulfoil.pdsapp.utils.isConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PdsViewModelImpl @Inject constructor(
    private val mainRepository: MainRepository
) : PdsViewModel, ViewModel() {
    override val pdsLiveData = MutableLiveData<PdsResponse>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val lastLanguageLiveData = MutableLiveData<Languages>()

    override fun getPds(oilId: Int) {
        progressLiveData.value = true
        if (!isConnected()) {
            progressLiveData.value = false
        } else {
            mainRepository.getPDS(oilId).onEach {
                it.onSuccess {
                    pdsLiveData.value = it
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