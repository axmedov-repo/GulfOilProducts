package com.gulfoil.pdsapp.data.entities

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class OilData(
    val id : Int,
    val name : String,
    val image : Int
) : Serializable