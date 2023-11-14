package com.gulfoil.pdsapp.screens.contacts.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.CountriesEnum
import com.gulfoil.pdsapp.data.enums.Languages

interface ContactsViewModel {
    val countryLiveData: LiveData<CountriesEnum>
    fun getCountry()
    fun setCountry(countriesEnum: CountriesEnum)

    val lastLanguageLiveData: LiveData<Languages>
    fun getLanguage()
}