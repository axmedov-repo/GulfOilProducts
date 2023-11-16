package com.gulfoil.pdsapp.data.remote.responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PdsResponse(
    @SerializedName("pdf")
    val pdf: String?
)