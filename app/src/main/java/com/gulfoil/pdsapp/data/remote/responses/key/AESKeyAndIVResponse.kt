package com.gulfoil.pdsapp.data.remote.responses.key

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AESKeyAndIVResponse(
    @SerializedName("key")
    val key: String,
    @SerializedName("iv")
    val iv: String
)
