package com.gulfoil.pdsapp.domain.key

import com.gulfoil.pdsapp.data.remote.responses.key.AESKeyAndIVResponse
import com.gulfoil.pdsapp.data.remote.responses.key.ExchangedGeneratedKeyResponse
import com.gulfoil.pdsapp.data.remote.responses.key.PublicKeysResponse
import kotlinx.coroutines.flow.Flow

interface KeyRepository {
    fun getPublicKeys(): Flow<Result<PublicKeysResponse>>
    fun exchangeGeneratedKeys(): Flow<Result<ExchangedGeneratedKeyResponse>>
    fun getAESKeyAndIV(): Flow<Result<AESKeyAndIVResponse>>
}
