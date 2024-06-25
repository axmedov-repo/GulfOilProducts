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
import com.gulfoil.pdsapp.utils.connection.safeApiCall
import com.gulfoil.pdsapp.utils.timber
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val localStorage: LocalStorage,
    private val keyStoreManager: KeyStoreManager
) : MainRepository {
    private val adsResponseList = ArrayList<AdResponseItem>()

    override fun getProducts(): Flow<Result<List<ProductResponseItem>>> =
        safeApiCall(localStorage) {
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
                    Result.success(parsedData)
                } else {
                    Result.failure(Throwable("${response.code()}"))
                }
            } else {
                Result.failure(Throwable("${response.code()}"))
            }
        }

    override fun getOils(productId: Int, name: String?): Flow<Result<List<OilResponseItem>>> =
        safeApiCall(localStorage) {
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
                    Result.success(parsedData)
                } else {
                    Result.failure(Throwable("${response.code()}"))
                }
            } else {
                Result.failure(Throwable("${response.code()}"))
            }
        }

    override fun getPDS(oilId: Int): Flow<Result<PdsResponse>> = safeApiCall(localStorage) {
        val response = productService.getPDS(localStorage.appLanguage.brief, oilId)
        if (response.isSuccessful && response.body() != null) {
            val parsedData = decryptAndParseData<PdsResponse>(
                encryptedData = response.body()!!,
                key = keyStoreManager.getKey(),
                iv = keyStoreManager.getIV()
            )
            if (parsedData != null) {
                Result.success(parsedData)
            } else {
                Result.failure(Throwable("${response.code()}"))
            }
        } else {
            Result.failure(Throwable("${response.code()}"))
        }
    }

    override fun getPublicContact(): Flow<Result<List<PublicContactResponseItem>>> =
        safeApiCall(localStorage) {
            val response = productService.getPublicContact(localStorage.appLanguage.brief)
            if (response.isSuccessful && response.body() != null) {
                val parsedData =
                    decryptAndParseData<PublicContactResponse>(
                        encryptedData = response.body()!!,
                        key = keyStoreManager.getKey(),
                        iv = keyStoreManager.getIV()
                    )
                if (parsedData != null) {
                    Result.success(parsedData)
                } else {
                    Result.failure(Throwable("${response.code()}"))
                }
            } else {
                Result.failure(Throwable("${response.code()}"))
            }
        }

    override fun getRegionalContact(regionCode: String): Flow<Result<List<RegionalContactResponseItem>>> =
        safeApiCall(localStorage) {
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
                    Result.success(parsedData)
                } else {
                    Result.failure(Throwable("${response.code()}"))
                }
            } else {
                Result.failure(Throwable("${response.code()}"))
            }
        }

    override fun getAds(): Flow<Result<List<AdResponseItem>>> = safeApiCall(localStorage) {
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
                    adsResponseList.addAll(parsedData)
                }
                val adResponse = AdResponse()
                adResponse.addAll(adsResponseList)
                Result.success(adResponse)
            } else {
                Result.failure(Throwable("${response.code()}"))
            }
        } else {
            val adResponse = AdResponse()
            adResponse.addAll(adsResponseList)
            Result.success(adResponse)
        }
    }

    override fun getLanguage(): Languages = localStorage.appLanguage

    override fun setLanguage(languages: Languages) {
        localStorage.appLanguage = languages
    }
}
