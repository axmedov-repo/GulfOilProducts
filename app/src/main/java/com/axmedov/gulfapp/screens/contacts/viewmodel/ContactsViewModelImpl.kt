package com.axmedov.gulfapp.screens.contacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axmedov.gulfapp.data.cache.LocalStorage
import com.axmedov.gulfapp.data.enums.CountriesEnum
import com.axmedov.gulfapp.data.enums.Languages

class ContactsViewModelImpl : ContactsViewModel, ViewModel() {
    private val pref = LocalStorage.getInstance()

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
