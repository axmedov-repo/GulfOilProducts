package com.gulfoil.pdsapp.data.aes

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
inline fun <reified T : Any> parseDecryptedAES(encryptedData: String): T? {
    return Gson().fromJson(decryptAES(encryptedData), T::class.java)
}
