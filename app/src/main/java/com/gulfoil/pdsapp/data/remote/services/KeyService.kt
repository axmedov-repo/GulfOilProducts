package com.gulfoil.pdsapp.data.remote.services

import com.gulfoil.pdsapp.data.remote.responses.key.AESKeyAndIVResponse
import com.gulfoil.pdsapp.data.remote.responses.key.ExchangedGeneratedKeyResponse
import com.gulfoil.pdsapp.data.remote.responses.key.PublicKeysResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface KeyService {

    @GET("en/api/v1/public-key/")
    suspend fun getPublicKeys(): Response<PublicKeysResponse>

    @GET("en/api/v1/exchange/")
    suspend fun exchangeGeneratedKeys(
        @Header("Client-Generated-Key") myGeneratedKey: Int
    ): Response<ExchangedGeneratedKeyResponse>

    @GET("en/api/v1/aes-key/")
    suspend fun getAESKeyAndIV(
        @Header("Client-Symmetric-Key") mySymmetricKey: Int
    ): Response<AESKeyAndIVResponse>
}
