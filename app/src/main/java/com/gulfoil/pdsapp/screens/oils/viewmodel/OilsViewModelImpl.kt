package com.gulfoil.pdsapp.screens.oils.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.enums.Languages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OilsViewModelImpl @Inject constructor(
    private val localStorage: LocalStorage
) : OilsViewModel, ViewModel() {

    override val lastLanguageLiveData = MutableLiveData<Languages>()

    override fun setLanguage(language: Languages) {
        localStorage.appLanguage = language
    }

    override fun getLanguage() {
        lastLanguageLiveData.value = localStorage.appLanguage
    }
}