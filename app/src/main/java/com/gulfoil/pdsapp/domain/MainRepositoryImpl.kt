package com.gulfoil.pdsapp.domain

import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.ApiService
import com.gulfoil.pdsapp.data.remote.responses.OilResponse
import com.gulfoil.pdsapp.data.remote.responses.PdsResponse
import com.gulfoil.pdsapp.data.remote.responses.ProductResponse
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponse
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localStorage: LocalStorage
) : MainRepository {
    override fun getProducts(): Flow<Result<ProductResponse>> = flow {
        val response = apiService.getProducts(localStorage.appLanguage.brief)
        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getOils(productId: Int, name: String?): Flow<Result<OilResponse>> = flow {
        val response = if (name.isNullOrEmpty()) {
            apiService.searchOil(localStorage.appLanguage.brief, productId)
        } else {
            apiService.getOils(localStorage.appLanguage.brief, productId, name)
        }

        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPDS(oilId: Int): Flow<Result<PdsResponse>> = flow {
        val response = apiService.getPDS(localStorage.appLanguage.brief, oilId)
        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getPublicContact(): Flow<Result<PublicContactResponse>> = flow {
        val response = apiService.getPublicContact(localStorage.appLanguage.brief)
        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getRegionalContact(regionCode: String): Flow<Result<RegionalContactResponse>> = flow {
        val response = apiService.getRegionalContact(localStorage.appLanguage.brief, regionCode)
        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("${response.code()}")))
        }
    }.flowOn(Dispatchers.IO)

    override fun getLanguage(): Languages = localStorage.appLanguage

    override fun setLanguage(languages: Languages) {
        localStorage.appLanguage = languages
    }
}