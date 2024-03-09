package com.gulfoil.pdsapp.data.remote

import android.util.Base64

object JWTDecoder {

    fun decodeJWT(jwtToken: String, secretKey: String): String? {
        val jwtParts = jwtToken.split("\\.".toRegex()).toTypedArray()

        try {
            // Decode and parse the header and payload
            val header = decodeBase64(jwtParts[0])
            val payload = decodeBase64(jwtParts[1])

            // Verify the signature (optional if needed)
            // val signature = jwtParts[2]

            // Convert JSON payload to a string
            return payload
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun decodeBase64(input: String): String {
        val decodedBytes = Base64.decode(input, Base64.URL_SAFE)
        return String(decodedBytes)
    }
}
