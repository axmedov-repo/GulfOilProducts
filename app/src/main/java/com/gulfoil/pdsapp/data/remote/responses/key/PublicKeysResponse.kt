package com.gulfoil.pdsapp.data.remote.responses.key

import com.google.gson.annotations.SerializedName

data class PublicKeysResponse(
    @SerializedName("first_key")
    val publicKey1: Int,
    @SerializedName("second_key")
    val publicKey2: Int
)
