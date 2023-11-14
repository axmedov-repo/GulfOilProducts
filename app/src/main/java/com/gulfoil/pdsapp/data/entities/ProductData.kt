package com.gulfoil.pdsapp.data.entities

import androidx.annotation.Keep
import com.gulfoil.pdsapp.data.enums.ProductTypes
import java.io.Serializable

@Keep
data class ProductData(
    val id: Int,
    val name: String,
    val image: Int,
    val productType: ProductTypes
) : Serializable