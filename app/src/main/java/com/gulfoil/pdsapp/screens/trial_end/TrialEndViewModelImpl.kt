package com.gulfoil.pdsapp.screens.trial_end

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.domain.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrialEndViewModelImpl @Inject constructor(
    private val mainRepository: MainRepository
) : TrialEndViewModel, ViewModel() {
    override val appLanguage = MutableLiveData<Languages>()

    override fun getAppLanguage() {
        viewModelScope.launch {
            appLanguage.value = mainRepository.getLanguage()
        }
    }
}
