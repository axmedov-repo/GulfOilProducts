package com.gulfoil.pdsapp.screens.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfoil.pdsapp.domain.key.KeyRepository
import com.gulfoil.pdsapp.utils.connection.isConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val keyRepository: KeyRepository
) : SplashViewModel, ViewModel() {
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLiveData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<Unit>()

    override fun exchangeEncryptionDetails() {
        progressLiveData.value = true
        if (!isConnected()) {
            progressLiveData.value = false
        } else {
            keyRepository.getPublicKeys().onEach { publicKeyResponse ->

                publicKeyResponse.onSuccess {
                    keyRepository.exchangeGeneratedKeys().onEach { exchangeGeneratedKeyResponse ->

                        exchangeGeneratedKeyResponse.onSuccess {
                            keyRepository.getAESKeyAndIV().onEach { aesKeyAndIVResponse ->

                                aesKeyAndIVResponse.onSuccess {
                                    successLiveData.value = Unit
                                    progressLiveData.value = false
                                }

                                aesKeyAndIVResponse.onFailure {
                                    errorLiveData.value = it.message
                                    progressLiveData.value = false
                                }

                            }.launchIn(viewModelScope)
                        }

                        exchangeGeneratedKeyResponse.onFailure {
                            errorLiveData.value = it.message
                            progressLiveData.value = false
                        }

                    }.launchIn(viewModelScope)
                }

                publicKeyResponse.onFailure {
                    errorLiveData.value = it.message
                    progressLiveData.value = false
                }

            }.launchIn(viewModelScope)
        }
    }
}
