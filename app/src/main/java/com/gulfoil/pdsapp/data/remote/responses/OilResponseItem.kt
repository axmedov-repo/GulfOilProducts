package com.gulfoil.pdsapp.data.remote.responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class OilResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Serializable
