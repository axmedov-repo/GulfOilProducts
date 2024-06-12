package com.gulfoil.pdsapp.domain.key

import com.gulfoil.pdsapp.data.cache.KeyStorage
import com.gulfoil.pdsapp.data.encryption.KeyStoreManager
import com.gulfoil.pdsapp.data.remote.responses.key.AESKeyAndIVResponse
import com.gulfoil.pdsapp.data.remote.responses.key.ExchangedGeneratedKeyResponse
import com.gulfoil.pdsapp.data.remote.responses.key.PublicKeysResponse
import com.gulfoil.pdsapp.data.remote.services.KeyService
import com.gulfoil.pdsapp.utils.timber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.random.Random

class KeyRepositoryImpl @Inject constructor(
    private val keyService: KeyService,
    private val keyStorage: KeyStorage,
    private val keyStoreManager: KeyStoreManager
) : KeyRepository {

    override fun getPublicKeys(): Flow<Result<PublicKeysResponse>> = flow {
        val response = keyService.getPublicKeys()
        if (response.isSuccessful && response.body() != null) {
            keyStorage.publicKey1 = response.body()!!.publicKey1.toLong()
            keyStorage.publicKey2 = response.body()!!.publicKey2.toLong()
            keyStorage.myPrivateKey = Random.nextInt(1, response.body()!!.publicKey1 - 2).toLong()

            timber(
                "keyStorage.myPrivateKey=${keyStorage.myPrivateKey}\n" +
                        "keyStorage.publicKey1=${keyStorage.publicKey1}\n" +
                        "keyStorage.publicKey2=${keyStorage.publicKey2}",
                "KEYSTORE_LOGS"
            )

            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun exchangeGeneratedKeys(): Flow<Result<ExchangedGeneratedKeyResponse>> = flow {
        keyStorage.myGeneratedKey =
            modPow(keyStorage.publicKey2, keyStorage.myPrivateKey, keyStorage.publicKey1)

        timber(
            "keyStorage.myGeneratedKey=${keyStorage.myGeneratedKey}",
            "KEYSTORE_LOGS"
        )

        val response = keyService.exchangeGeneratedKeys(keyStorage.myGeneratedKey.toInt())
        if (response.isSuccessful && response.body() != null) {
            keyStorage.serverGeneratedKey = response.body()!!.serverGeneratedKey.toLong()

            timber(
                "keyStorage.serverGeneratedKey=${keyStorage.serverGeneratedKey}",
                "KEYSTORE_LOGS"
            )

            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAESKeyAndIV(): Flow<Result<AESKeyAndIVResponse>> = flow {
        // Focus Math Operations Precedence
        keyStorage.sharedSymmetricKey =
            modPow(keyStorage.serverGeneratedKey, keyStorage.myPrivateKey, keyStorage.publicKey1)

        timber("keyStorage.sharedSymmetricKey=${keyStorage.sharedSymmetricKey}", "KEYSTORE_LOGS")

        val response = keyService.getAESKeyAndIV(keyStorage.sharedSymmetricKey.toInt())
        if (response.isSuccessful && response.body() != null) {

            timber("keyStoreManager in KeyRepo=${keyStoreManager.hashCode()}", "KEYSTORE_LOGS")
            timber(
                "\nresponeKey=${response.body()!!.key}" +
                        "\nresponeIv=${response.body()!!.iv}\n",
                "KEYSTORE_LOGS"
            )

            keyStoreManager.setIV(response.body()!!.iv)
            keyStoreManager.setKey(response.body()!!.key)

            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)
}

private fun modPow(base: Long, exponent: Long, modulus: Long): Long {
    var result = 1L
    var b = base % modulus
    var e = exponent

    while (e > 0) {
        if (e % 2 == 1L) {
            result = (result * b) % modulus
        }
        e = e shr 1
        b = (b * b) % modulus
    }

    return result
}
