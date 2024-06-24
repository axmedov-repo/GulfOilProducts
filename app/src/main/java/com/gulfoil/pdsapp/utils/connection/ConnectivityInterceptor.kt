package com.gulfoil.pdsapp.utils.connection

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.UnknownHostException

class ConnectivityInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: UnknownHostException) {
            // Handle UnknownHostException here
            throw UnknownHostException()
        } catch (e: IOException) {
            // Handle the IOException here
            throw IOException(e)
        }
        return response
    }
}
