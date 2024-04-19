package com.gulfoil.pdsapp.domain.main

import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.encryption.KeyStoreManager
import com.gulfoil.pdsapp.data.encryption.decryptAndParseData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.product.AdResponse
import com.gulfoil.pdsapp.data.remote.responses.product.AdResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.OilResponse
import com.gulfoil.pdsapp.data.remote.responses.product.OilResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.PdsResponse
import com.gulfoil.pdsapp.data.remote.responses.product.ProductResponse
import com.gulfoil.pdsapp.data.remote.responses.product.ProductResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.PublicContactResponse
import com.gulfoil.pdsapp.data.remote.responses.product.PublicContactResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.RegionalContactResponse
import com.gulfoil.pdsapp.data.remote.responses.product.RegionalContactResponseItem
import com.gulfoil.pdsapp.data.remote.services.ProductService
import com.gulfoil.pdsapp.utils.timber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val localStorage: LocalStorage,
    private val keyStoreManager: KeyStoreManager
) : MainRepository {
    private val adsResponseList = ArrayList<AdResponseItem>()

    override fun getProducts(): Flow<Result<List<ProductResponseItem>>> = flow {
        val response = productService.getProducts(localStorage.appLanguage.brief)
        if (response.isSuccessful && response.body() != null) {
            timber("keyStoreManager in MainRepo=${keyStoreManager.hashCode()}", "KEYSTORE_LOGS")
            timber(
                "keyStoreManager.getKey()=${keyStoreManager.getKey()}\nkeyStoreManager.getIV()=${keyStoreManager.getIV()}",
                "KEYSTORE_LOGS"
            )
            val parsedData = decryptAndParseData<ProductResponse>(
                encryptedData = response.body()!!,
                key = keyStoreManager.getKey(),
                iv = keyStoreManager.getIV()
            )
            if (parsedData != null) {
                emit(Result.success(parsedData))
            } else {
                emit(Result.failure(Throwable("${response.code()}")))
            }
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getOils(productId: Int, name: String?): Flow<Result<List<OilResponseItem>>> =
        flow {
            val response = if (name.isNullOrEmpty()) {
                productService.searchOil(localStorage.appLanguage.brief, productId)
            } else {
                productService.getOils(localStorage.appLanguage.brief, productId, name)
            }

            if (response.isSuccessful && response.body() != null) {
                val parsedData =
                    decryptAndParseData<OilResponse>(
                        encryptedData = response.body()!!,
                        key = keyStoreManager.getKey(),
                        iv = keyStoreManager.getIV()
                    )
                if (parsedData != null) {
                    emit(Result.success(parsedData))
                } else {
                    emit(Result.failure(Throwable("${response.code()}")))
                }
            } else {
                emit(Result.failure(Throwable("${response.code()}")))
            }
        }.flowOn(Dispatchers.IO)

    override fun getPDS(oilId: Int): Flow<Result<PdsResponse>> = flow {
        val response = productService.getPDS(localStorage.appLanguage.brief, oilId)
        if (response.isSuccessful && response.body() != null) {
            val parsedData = decryptAndParseData<PdsResponse>(
                encryptedData = response.body()!!,
                key = keyStoreManager.getKey(),
                iv = keyStoreManager.getIV()
            )
            if (parsedData != null) {
                emit(Result.success(parsedData))
            } else {
                emit(Result.failure(Throwable("${response.code()}")))
            }
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPublicContact(): Flow<Result<List<PublicContactResponseItem>>> = flow {
        val response = productService.getPublicContact(localStorage.appLanguage.brief)
        if (response.isSuccessful && response.body() != null) {
            val parsedData =
                decryptAndParseData<PublicContactResponse>(
                    encryptedData = response.body()!!,
                    key = keyStoreManager.getKey(),
                    iv = keyStoreManager.getIV()
                )
            if (parsedData != null) {
                emit(Result.success(parsedData))
            } else {
                emit(Result.failure(Throwable("${response.code()}")))
            }
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getRegionalContact(regionCode: String): Flow<Result<List<RegionalContactResponseItem>>> =
        flow {
            val response = productService.getRegionalContact(
                localStorage.appLanguage.brief, regionCode.uppercase()
            )
            if (response.isSuccessful && response.body() != null) {
                val parsedData =
                    decryptAndParseData<RegionalContactResponse>(
                        encryptedData = response.body()!!,
                        key = keyStoreManager.getKey(),
                        iv = keyStoreManager.getIV()
                    )
                if (parsedData != null) {
                    emit(Result.success(parsedData))
                } else {
                    emit(Result.failure(Throwable("${response.code()}")))
                }
            } else {
                emit(Result.failure(Throwable("${response.code()}")))
            }
        }.flowOn(Dispatchers.IO)

    override fun getAds(): Flow<Result<List<AdResponseItem>>> = flow {
        if (adsResponseList.isEmpty()) {
            val response = productService.getAds(localStorage.appLanguage.brief)
            if (response.isSuccessful && response.body() != null) {
                adsResponseList.clear()
                val parsedData =
                    decryptAndParseData<AdResponse>(
                        encryptedData = response.body()!!,
                        key = keyStoreManager.getKey(),
                        iv = keyStoreManager.getIV()
                    )
                if (parsedData != null) {
                    repeat(3) {
                        adsResponseList.addAll(parsedData)
                    }
                }
                val adResponse = AdResponse()
                adResponse.addAll(adsResponseList)
                emit(Result.success(adResponse))
            } else {
                emit(Result.failure(Throwable("${response.code()}")))
            }
        } else {
            val adResponse = AdResponse()
            adResponse.addAll(adsResponseList)
            emit(Result.success(adResponse))
        }
    }.flowOn(Dispatchers.IO)

    override fun getLanguage(): Languages = localStorage.appLanguage

    override fun setLanguage(languages: Languages) {
        localStorage.appLanguage = languages
    }
}
