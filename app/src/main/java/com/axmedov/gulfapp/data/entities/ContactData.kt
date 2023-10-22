package com.axmedov.gulfapp.data.entities

import androidx.annotation.Keep

@Keep
data class ContactData(
    val id : Int,
    val location : String,
    val name : String,
    val phone : String
)
