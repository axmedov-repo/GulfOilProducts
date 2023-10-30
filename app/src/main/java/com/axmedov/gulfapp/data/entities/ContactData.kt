package com.axmedov.gulfapp.data.entities

import androidx.annotation.Keep
import com.axmedov.gulfapp.data.enums.CountriesEnum

@Keep
data class ContactData(
    val location: String,
    val name: String,
    val phone: String,
    val email: String = "",
    val country: CountriesEnum
)
