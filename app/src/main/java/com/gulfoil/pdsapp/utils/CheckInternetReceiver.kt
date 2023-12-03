package com.gulfoil.pdsapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

private var internetConnectionListener: ((Boolean) -> Unit)? = null
fun setInternetConnectionListener(f: (Boolean) -> Unit) {
    internetConnectionListener = f
}

fun checkInternetConnection() {
    timber("Called checkInternetConnection() method. Result=${isConnected()}", "sdjskjdksjd")
    internetConnectionListener?.invoke(isConnected())
}

@AndroidEntryPoint
class CheckInternetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        checkInternetConnection()
    }
}
