package com.gulfoil.pdsapp.data.remote.responses.product

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RegionalContactResponseItem(
    @SerializedName("address")
    val location: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("staff")
    val name: String?,
    @SerializedName("phone")
    val phone: String?
)