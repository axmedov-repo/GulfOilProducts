package com.gulfoil.pdsapp.data.remote.responses.product

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PublicContactResponseItem(
    @SerializedName("address")
    val address: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("phone")
    val phone: String?
)