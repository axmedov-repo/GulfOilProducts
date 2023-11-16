package com.gulfoil.pdsapp.screens.contacts.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponse
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponse

interface ContactsViewModel {
    val publicContactsLiveData: LiveData<PublicContactResponse>
    val regionalContactsLiveData: LiveData<RegionalContactResponse>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val lastLanguageLiveData: LiveData<Languages>

    fun getPublicContacts()
    fun getRegionalContacts(regionCode: String)
    fun getLanguage()
}
