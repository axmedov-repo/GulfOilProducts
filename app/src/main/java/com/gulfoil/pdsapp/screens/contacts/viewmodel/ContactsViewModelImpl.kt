package com.gulfoil.pdsapp.screens.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.enums.CountriesEnum
import com.gulfoil.pdsapp.data.enums.Languages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsViewModelImpl @Inject constructor(
    private val pref: LocalStorage
) : ContactsViewModel, ViewModel() {

    override val countryLiveData = MutableLiveData<CountriesEnum>()

    override fun getCountry() {
        countryLiveData.value = pref.country
    }

    override fun setCountry(countriesEnum: CountriesEnum) {
        pref.country = countriesEnum
    }

    override val lastLanguageLiveData = MutableLiveData<Languages>()

    override fun getLanguage() {
        lastLanguageLiveData.value = pref.appLanguage
    }
}
