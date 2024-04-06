package com.gulfoil.pdsapp.data.remote.jwt

import android.util.Base64
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.gulfoil.pdsapp.app.App
import com.gulfoil.pdsapp.utils.timber
import java.io.BufferedReader
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.interfaces.RSAPrivateKey
import java.security.spec.PKCS8EncodedKeySpec

// This is faster but simple
object JWTDecoderHS256 {

    fun decodeJWT(jwtToken: String): String? {
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

object JWTDecoderRS256 {
    private fun loadPrivateKeyFromPEM(fileName: String): PrivateKey? {
        try {
            val inputStream = App.instance.assets.open(fileName)
            val pemContent = inputStream.bufferedReader().use(BufferedReader::readText)
                .replace("-----BEGIN ENCRYPTED PRIVATE KEY-----", "")
                .replace("-----END ENCRYPTED PRIVATE KEY-----", "")
                .replace("\n", "")
            timber("pemContent=$pemContent", "decodeJWT_Logs")

            val privateKeyBytes = Base64.decode(pemContent, Base64.DEFAULT)
            timber("privateKeyBytes=${privateKeyBytes.joinToString(", ")}", "decodeJWT_Logs")

            val keySpec = PKCS8EncodedKeySpec(privateKeyBytes)
            val keyFactory = KeyFactory.getInstance("RSA")
            return keyFactory.generatePrivate(keySpec)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    // Function to decode JWT token
    fun decodeJWT(jwtToken: String): String? {
        try {
            val fileName = "rsa_private_key.pem"

            val privateKey = loadPrivateKeyFromPEM(fileName) // Your private key file name
            timber("privateKey=$privateKey", "decodeJWT_Logs")

            val algorithm = Algorithm.RSA256(null, privateKey as RSAPrivateKey?)
            val verifier = JWT.require(algorithm).build()
            val decodedJWT = verifier.verify(jwtToken)
            timber("decodedJWT=$decodedJWT", "decodeJWT_Logs")

            return decodedJWT.payload
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
