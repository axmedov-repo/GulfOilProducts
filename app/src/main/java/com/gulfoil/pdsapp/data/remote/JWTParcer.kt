package com.gulfoil.pdsapp.data.remote

import com.google.gson.Gson
import org.json.JSONException

inline fun <reified T : Any> parseJWT(jwtToken: String, secretKey: String): T? {
    val jsonData = JWTDecoder.decodeJWT(jwtToken, secretKey)
    jsonData?.let {
        try {
            return Gson().fromJson(it, T::class.java)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    return null
}

inline fun <reified T : Any> parseJWTList(jwtTokenList: List<String>, secretKey: String): List<T> {
    val resultArrayList = ArrayList<T>()

    jwtTokenList.forEach { jwtToken ->
        val jsonData = JWTDecoder.decodeJWT(jwtToken, secretKey)

        jsonData?.let {
            try {
                resultArrayList.add(
                    Gson().fromJson(it, T::class.java)
                )
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
    return resultArrayList
}
