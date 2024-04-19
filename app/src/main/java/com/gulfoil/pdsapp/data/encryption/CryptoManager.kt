package com.gulfoil.pdsapp.data.encryption

import com.google.gson.Gson
import com.gulfoil.pdsapp.data.encryption.aes_modes.CBC

inline fun <reified T : Any> decryptAndParseData(
    encryptedData: String, key: String?, iv: String?
): T? {
    return Gson().fromJson(decryptData(encryptedData, key, iv), T::class.java)
}

fun decryptData(encryptedData: String, key: String?, iv: String?): String? {
    return try {
        CBC.decryptAES(encryptedData, key, iv)
    } catch (e: Exception) {
        null
    }
}
