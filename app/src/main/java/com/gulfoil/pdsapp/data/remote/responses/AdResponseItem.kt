package com.gulfoil.pdsapp.data.remote.responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AdResponseItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?
)