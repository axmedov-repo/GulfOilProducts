package com.gulfoil.pdsapp.data.encryption

import android.util.Base64
import com.gulfoil.pdsapp.data.cache.KeyStorage
import com.gulfoil.pdsapp.utils.timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeyStoreManager @Inject constructor(
    private val keyCache: KeyStorage,
    private val keyManager: KeyManager
) {
    fun setKey(key: String) {
        try {
            keyCache.encryptedKeyForAESKey =
                Base64.encodeToString(keyManager.encrypt(key.toByteArray()), Base64.DEFAULT)
        } catch (e: Exception) {
            timber("Error storing key: ${e.message}", "KEYSTORE_LOGS")
        }
    }

    fun getKey(): String? {
        return try {
            val encryptedKeyForAESKey = keyCache.encryptedKeyForAESKey
            if (encryptedKeyForAESKey != null) {
                val encryptedKey = Base64.decode(encryptedKeyForAESKey, Base64.DEFAULT)
                val decryptedKey = String(keyManager.decrypt(encryptedKey))
                decryptedKey
            } else {
                null
            }
        } catch (e: java.lang.Exception) {
            null
        }
    }

    fun setIV(iv: String) {
        keyCache.iv = iv
    }

    fun getIV(): String? = keyCache.iv
}
