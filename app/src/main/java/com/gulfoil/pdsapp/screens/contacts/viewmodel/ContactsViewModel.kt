package com.gulfoil.pdsapp.screens.contacts.viewmodel

import androidx.lifecycle.LiveData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponseItem
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponseItem

interface ContactsViewModel {
    val publicContactsLiveData: LiveData<List<PublicContactResponseItem>>
    val regionalContactsLiveData: LiveData<List<RegionalContactResponseItem>>
    val progressLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val lastLanguageLiveData: LiveData<Languages>
    val connectionLiveData: LiveData<Boolean>

    fun getPublicContacts()
    fun getRegionalContacts(regionCode: String)
    fun getLanguage()
}
