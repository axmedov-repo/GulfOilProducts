package com.axmedov.gulfapp.data.entities

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class NewOilData(
    val id : Int,
    val name : String,
    val segment : String,
    val pdsLink : String
) : Serializable
