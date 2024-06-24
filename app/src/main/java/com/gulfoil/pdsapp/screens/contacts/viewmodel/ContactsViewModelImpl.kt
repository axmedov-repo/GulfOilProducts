package com.gulfoil.pdsapp.screens.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.product.PublicContactResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.RegionalContactResponseItem
import com.gulfoil.pdsapp.domain.main.MainRepository
import com.gulfoil.pdsapp.utils.connection.isConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactsViewModelImpl @Inject constructor(
    private val mainRepository: MainRepository
) : ContactsViewModel, ViewModel() {

    override val publicContactsLiveData = MutableLiveData<List<PublicContactResponseItem>>()
    override val regionalContactsLiveData = MutableLiveData<List<RegionalContactResponseItem>>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val lastLanguageLiveData = MutableLiveData<Languages>()
    override val connectionLiveData = MutableLiveData<Boolean>()

    override fun getPublicContacts() {
        progressLiveData.value = true
        val isConnected = isConnected()
        connectionLiveData.value = isConnected
        if (!isConnected) {
            progressLiveData.value = false
        } else {
            mainRepository.getPublicContact().onEach {
                it.onSuccess {
                    publicContactsLiveData.value = it
                    progressLiveData.value = false
                }
                it.onFailure {
                    errorLiveData.value = it.message
                    progressLiveData.value = false
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun getRegionalContacts(regionCode: String) {
        progressLiveData.value = true
        val isConnected = isConnected()
        connectionLiveData.value = isConnected
        if (!isConnected) {
            progressLiveData.value = false
        } else {
            mainRepository.getRegionalContact(regionCode).onEach {
                it.onSuccess {
                    regionalContactsLiveData.value = it
                    progressLiveData.value = false
                }
                it.onFailure {
                    errorLiveData.value = it.message
                    progressLiveData.value = false
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun getLanguage() {
        lastLanguageLiveData.value = mainRepository.getLanguage()
    }
}
