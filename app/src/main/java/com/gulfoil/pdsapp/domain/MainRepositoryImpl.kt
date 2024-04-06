package com.gulfoil.pdsapp.domain

import com.gulfoil.pdsapp.data.aes.parseDecryptedAES
import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.AdResponse
import com.gulfoil.pdsapp.data.remote.responses.AdResponseItem
import com.gulfoil.pdsapp.data.remote.responses.OilResponse
import com.gulfoil.pdsapp.data.remote.responses.OilResponseItem
import com.gulfoil.pdsapp.data.remote.responses.PdsResponse
import com.gulfoil.pdsapp.data.remote.responses.ProductResponse
import com.gulfoil.pdsapp.data.remote.responses.ProductResponseItem
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponse
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponseItem
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponse
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponseItem
import com.gulfoil.pdsapp.data.remote.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localStorage: LocalStorage
) : MainRepository {
    private val adsResponseList = ArrayList<AdResponseItem>()

    override fun getProducts(): Flow<Result<List<ProductResponseItem>>> = flow {
        val response = apiService.getProducts(localStorage.appLanguage.brief)
        if (response.isSuccessful && response.body() != null) {
            val parsedData =
                parseDecryptedAES<ProductResponse>(response.body()!!)
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
                apiService.searchOil(localStorage.appLanguage.brief, productId)
            } else {
                apiService.getOils(localStorage.appLanguage.brief, productId, name)
            }

            if (response.isSuccessful && response.body() != null) {
                val parsedData =
                    parseDecryptedAES<OilResponse>(response.body()!!)
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
        val response = apiService.getPDS(localStorage.appLanguage.brief, oilId)
        if (response.isSuccessful && response.body() != null) {
            val parsedData = parseDecryptedAES<PdsResponse>(response.body()!!)
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
        val response = apiService.getPublicContact(localStorage.appLanguage.brief)
        if (response.isSuccessful && response.body() != null) {
            val parsedData =
                parseDecryptedAES<PublicContactResponse>(response.body()!!)
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
            val response = apiService.getRegionalContact(
                localStorage.appLanguage.brief, regionCode.uppercase()
            )
            if (response.isSuccessful && response.body() != null) {
                val parsedData =
                    parseDecryptedAES<RegionalContactResponse>(
                        response.body()!!
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
            val response = apiService.getAds(localStorage.appLanguage.brief)
            if (response.isSuccessful && response.body() != null) {
                adsResponseList.clear()
                val parsedData =
                    parseDecryptedAES<AdResponse>(response.body()!!)
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
