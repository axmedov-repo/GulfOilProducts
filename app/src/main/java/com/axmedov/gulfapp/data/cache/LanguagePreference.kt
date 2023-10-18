package com.axmedov.gulfapp.data.cache

import com.axmedov.gulfapp.app.App
import com.axmedov.gulfapp.data.enums.Languages
import com.securepreferences.SecurePreferences

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

class LanguagePreference private constructor() {
    private val KEY = "hsdhshdjkahsds54d5sa4d54sa5d45s4d5s4d5s4d5s4dd"
    private val pref = SecurePreferences(App.instance, KEY, "language_preference.xml")

    companion object {
        private lateinit var instance: LanguagePreference

        fun getInstance(): LanguagePreference {
            if (!::instance.isInitialized) {
                instance = LanguagePreference()
            }
            return instance
        }
    }


    var appLanguage: Languages
        set(value) = pref.edit().putString("appLanguage", value.brief).apply()
        get() =
            if (pref.getString("appLanguage", Languages.ENGLISH.brief).equals(Languages.ENGLISH.brief)) {
                Languages.ENGLISH
            } else {
                Languages.RUSSIAN
            }

}
