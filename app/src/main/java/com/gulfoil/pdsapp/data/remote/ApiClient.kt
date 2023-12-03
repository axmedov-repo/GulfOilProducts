package com.gulfoil.pdsapp.data.remote

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.gulfoil.pdsapp.BuildConfig.BASE_URL
import com.gulfoil.pdsapp.BuildConfig.LOGGING
import com.gulfoil.pdsapp.app.App
import com.gulfoil.pdsapp.utils.timber
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(getHttpClient())
        .build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addChucker()
            .addLogging()
            .build()
    }
}

private fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
    HttpLoggingInterceptor.Level.HEADERS
    val logging = HttpLoggingInterceptor.Logger { message -> timber(message, "HTTP") }
    if (LOGGING) {
        addInterceptor(HttpLoggingInterceptor(logging))
    }
    return this
}

private fun OkHttpClient.Builder.addChucker(): OkHttpClient.Builder {
    if (LOGGING) {
        addInterceptor(
            ChuckerInterceptor.Builder(App.instance)
                .collector(ChuckerCollector(App.instance))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
    }
    return this
}
