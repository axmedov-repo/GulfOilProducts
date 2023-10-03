package com.axmedov.gulfapp.data.entities

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class SubProductData(
    val id : Int,
    val name : String
) : Serializable