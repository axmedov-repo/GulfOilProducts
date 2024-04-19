package com.gulfoil.pdsapp.data.remote.responses.key

import com.google.gson.annotations.SerializedName

data class ExchangedGeneratedKeyResponse(
    @SerializedName("my_generated_key")
    val serverGeneratedKey: Int
)
