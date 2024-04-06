package com.gulfoil.pdsapp.data.remote.jwt

import com.google.gson.Gson
import org.json.JSONException

inline fun <reified T : Any> parseJWT(jwtToken: String): T? {
    JWTDecoderHS256.decodeJWT(jwtToken)?.let { decodedJWT ->
        val jsonData = Gson().toJson(decodedJWT)

        jsonData?.let {
            try {
                return Gson().fromJson(it, T::class.java)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
    return null
}

inline fun <reified T : Any> parseJWTList(jwtTokenList: List<String>): List<T> {
    val resultArrayList = ArrayList<T>()

    jwtTokenList.forEach { jwtToken ->
        JWTDecoderHS256.decodeJWT(jwtToken)?.let { decodedJWT ->
            val jsonData = Gson().toJson(decodedJWT)

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
    }
    return resultArrayList
}
