package com.gulfoil.pdsapp.data.cache

import com.gulfoil.pdsapp.app.App
import com.securepreferences.SecurePreferences
import javax.inject.Singleton

@Singleton
class KeyStorage {
    private val KEY = "skjdksjdkjksdjskjdksjdkjskdjksjdksj"
    private val pref = SecurePreferences(App.instance, KEY, "preference2.xml")

//    companion object {
//        private lateinit var instance: IVStorage
//
//        fun getInstance(): IVStorage {
//            if (!::instance.isInitialized) {
//                instance = IVStorage()
//            }
//            return instance
//        }
//    }

    var publicKey1: Long
        set(value) = pref.edit().putLong("publicKey1", value).apply()
        get() = pref.getLong("publicKey1", -1)

    var publicKey2: Long
        set(value) = pref.edit().putLong("publicKey2", value).apply()
        get() = pref.getLong("publicKey2", -1)

    var myPrivateKey: Long
        set(value) = pref.edit().putLong("myPrivateKey", value).apply()
        get() = pref.getLong("myPrivateKey", -1)

    var myGeneratedKey: Long
        set(value) = pref.edit().putLong("myGeneratedKey", value).apply()
        get() = pref.getLong("myGeneratedKey", -1)

    var serverGeneratedKey: Long
        set(value) = pref.edit().putLong("serverGeneratedKey", value).apply()
        get() = pref.getLong("serverGeneratedKey", -1)

    var sharedSymmetricKey: Long
        set(value) = pref.edit().putLong("sharedSymmetricKey", value).apply()
        get() = pref.getLong("sharedSymmetricKey", -1)

    var iv: String?
        set(value) = pref.edit().putString("iv", value).apply()
        get() = pref.getString("iv", "")

    var encryptedKeyForAESKey: String?
        set(value) = pref.edit().putString("encryptedKeyForAESKey", value).apply()
        get() = pref.getString("encryptedKeyForAESKey", null)

    var ivForEncryptingAESKey: String?
        set(value) = pref.edit().putString("encryptedIVForAESKey", value).apply()
        get() = pref.getString("encryptedIVForAESKey", null)
}
