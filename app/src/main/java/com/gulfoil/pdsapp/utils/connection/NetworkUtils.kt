package com.gulfoil.pdsapp.utils.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.app.App
import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.enums.Languages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.net.UnknownHostException

// Network Utility to detect availability or unavailability of Internet connection

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

object NetworkUtils : ConnectivityManager.NetworkCallback() {

    private val networkLiveData: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Returns network connectivity state
     */
    fun getNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), this)
        }

        var isConnected = false

        // Retrieve current status of connectivity
        connectivityManager.allNetworks.forEach { network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)

            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    isConnected = true
                    return@forEach
                }
            }
        }
        return isConnected
    }

    /**
     * Returns instance of [LiveData] which can be observed for network changes.
     */
    fun getNetworkLiveData(context: Context): LiveData<Boolean> {

        networkLiveData.postValue(getNetwork(context))

        return networkLiveData
    }

    override fun onAvailable(network: Network) {
        networkLiveData.postValue(true)
    }

    override fun onLost(network: Network) {
        networkLiveData.postValue(false)
    }

}

fun <T> safeApiCall(
    localStorage: LocalStorage,
    apiCall: suspend () -> Result<T>
): Flow<Result<T>> = flow {
    try {
        emit(apiCall())
    } catch (e: UnknownHostException) {
        emit(
            Result.failure(
                Throwable(
                    getString(
                        App.instance,
                        if (localStorage.appLanguage == Languages.ENGLISH) R.string.error_unable_to_resolve_host_en
                        else R.string.error_unable_to_resolve_host_ru
                    )
                )
            )
        )
    } catch (e: IOException) {
        emit(
            Result.failure(
                Throwable(
                    getString(
                        App.instance,
                        if (localStorage.appLanguage == Languages.ENGLISH) R.string.error_network_en
                        else R.string.error_network_ru
                    )
                )
            )
        )
    } catch (e: Exception) {
        emit(
            Result.failure(
                Throwable(
                    String.format(
                        getString(
                            App.instance,
                            if (localStorage.appLanguage == Languages.ENGLISH) R.string.error_unexpected_en
                            else R.string.error_unexpected_ru,
                        ),
                        e.message
                    )
                )
            )
        )
    }
}.flowOn(Dispatchers.IO).catch { e ->
    emit(
        Result.failure(
            Throwable(
                String.format(
                    getString(
                        App.instance,
                        if (localStorage.appLanguage == Languages.ENGLISH) R.string.error_unexpected_en
                        else R.string.error_unexpected_ru,
                    ),
                    e.message
                )
            )
        )
    )
}
