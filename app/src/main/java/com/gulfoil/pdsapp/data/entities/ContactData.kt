package com.gulfoil.pdsapp.data.entities

import androidx.annotation.Keep
import com.gulfoil.pdsapp.data.enums.CountriesEnum

@Keep
data class ContactData(
    val location: String,
    val name: String,
    val phone: String,
    val email: String = "",
    val country: CountriesEnum
)
