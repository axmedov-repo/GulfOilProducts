package com.gulfoil.pdsapp.domain

import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.ApiService
import com.gulfoil.pdsapp.data.remote.parseJWT
import com.gulfoil.pdsapp.data.remote.parseJWTList
import com.gulfoil.pdsapp.data.remote.responses.AdResponse
import com.gulfoil.pdsapp.data.remote.responses.AdResponseItem
import com.gulfoil.pdsapp.data.remote.responses.OilResponseItem
import com.gulfoil.pdsapp.data.remote.responses.PdsResponse
import com.gulfoil.pdsapp.data.remote.responses.ProductResponseItem
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponseItem
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponseItem
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
    private val SECRET_KEY = "django-insecure-_eg!e&8w5%ysdozjw#j@9ct)u&1u8wgludh@^*qgb#8+tfo&yh"

    override fun getProducts(): Flow<Result<List<ProductResponseItem>>> = flow {
        val response = apiService.getProducts(localStorage.appLanguage.brief)
        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(parseJWTList<ProductResponseItem>(response.body()!!, SECRET_KEY)))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getOils(productId: Int, name: String?): Flow<Result<List<OilResponseItem>>> = flow {
        val response = if (name.isNullOrEmpty()) {
            apiService.searchOil(localStorage.appLanguage.brief, productId)
        } else {
            apiService.getOils(localStorage.appLanguage.brief, productId, name)
        }

        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(parseJWTList<OilResponseItem>(response.body()!!, SECRET_KEY)))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPDS(oilId: Int): Flow<Result<PdsResponse>> = flow {
        val response = apiService.getPDS(localStorage.appLanguage.brief, oilId)
        if (response.isSuccessful && response.body() != null) {
            if (parseJWT<PdsResponse>(response.body()!!, SECRET_KEY) != null) {
                emit(Result.success(parseJWT<PdsResponse>(response.body()!!, SECRET_KEY)!!))
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
            emit(Result.success(parseJWTList<PublicContactResponseItem>(response.body()!!, SECRET_KEY)))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getRegionalContact(regionCode: String): Flow<Result<List<RegionalContactResponseItem>>> = flow {
        val response = apiService.getRegionalContact(localStorage.appLanguage.brief, regionCode.uppercase())
        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(parseJWTList<RegionalContactResponseItem>(response.body()!!, SECRET_KEY)))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAds(): Flow<Result<List<AdResponseItem>>> = flow {
        if (adsResponseList.isEmpty()) {
            val response = apiService.getAds(localStorage.appLanguage.brief)
            if (response.isSuccessful && response.body() != null) {
                adsResponseList.clear()
                repeat(3) {
                    adsResponseList.addAll(parseJWTList<AdResponseItem>(response.body()!!, SECRET_KEY))
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