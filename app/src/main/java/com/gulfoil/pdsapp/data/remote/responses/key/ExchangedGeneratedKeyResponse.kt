package com.gulfoil.pdsapp.data.remote.responses.key

import com.google.errorprone.annotations.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ExchangedGeneratedKeyResponse(
    @SerializedName("my_generated_key")
    val serverGeneratedKey: Int
)
