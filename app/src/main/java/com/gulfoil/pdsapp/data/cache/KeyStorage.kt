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

    var publicKey1: Int
        set(value) = pref.edit().putInt("publicKey1", value).apply()
        get() = pref.getInt("publicKey1", -1)

    var publicKey2: Int
        set(value) = pref.edit().putInt("publicKey2", value).apply()
        get() = pref.getInt("publicKey2", -1)

    var myPrivateKey: Int
        set(value) = pref.edit().putInt("myPrivateKey", value).apply()
        get() = pref.getInt("myPrivateKey", -1)

    var myGeneratedKey: Int
        set(value) = pref.edit().putInt("myGeneratedKey", value).apply()
        get() = pref.getInt("myGeneratedKey", -1)

    var serverGeneratedKey: Int
        set(value) = pref.edit().putInt("serverGeneratedKey", value).apply()
        get() = pref.getInt("serverGeneratedKey", -1)

    var sharedSymmetricKey: Int
        set(value) = pref.edit().putInt("sharedSymmetricKey", value).apply()
        get() = pref.getInt("sharedSymmetricKey", -1)

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
