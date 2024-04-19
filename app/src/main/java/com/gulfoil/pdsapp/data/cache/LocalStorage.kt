package com.gulfoil.pdsapp.data.cache

import com.gulfoil.pdsapp.app.App
import com.gulfoil.pdsapp.data.enums.Languages
import com.securepreferences.SecurePreferences
import javax.inject.Singleton

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

@Singleton
class LocalStorage {
    private val KEY = "hsdhshdjkahsds54d5sa4d54sa5d45s4d5s4d5s4d5s4dd"
    private val pref = SecurePreferences(App.instance, KEY, "preference1.xml")

    var appLanguage: Languages
        set(value) = pref.edit().putString("appLanguage", value.brief).apply()
        get() =
            if (pref.getString("appLanguage", Languages.ENGLISH.brief)
                    .equals(Languages.ENGLISH.brief)
            ) {
                Languages.ENGLISH
            } else {
                Languages.RUSSIAN
            }
}
