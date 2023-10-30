package com.axmedov.gulfapp.screens.contacts.viewmodel

import androidx.lifecycle.LiveData
import com.axmedov.gulfapp.data.enums.CountriesEnum
import com.axmedov.gulfapp.data.enums.Languages

interface ContactsViewModel {
    val countryLiveData: LiveData<CountriesEnum>
    fun getCountry()
    fun setCountry(countriesEnum: CountriesEnum)

    val lastLanguageLiveData: LiveData<Languages>
    fun getLanguage()
}