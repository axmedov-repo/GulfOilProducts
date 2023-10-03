package com.axmedov.gulfapp.data.entities

import androidx.annotation.Keep
import com.axmedov.gulfapp.data.enums.ProductTypes
import java.io.Serializable

@Keep
data class ProductData(
    val id: Int,
    val name: String,
    val image: Int,
    val productType: ProductTypes
) : Serializable