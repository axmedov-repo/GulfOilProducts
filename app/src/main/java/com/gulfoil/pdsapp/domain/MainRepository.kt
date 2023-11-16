package com.gulfoil.pdsapp.domain

import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.OilResponse
import com.gulfoil.pdsapp.data.remote.responses.PdsResponse
import com.gulfoil.pdsapp.data.remote.responses.ProductResponse
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponse
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProducts(): Flow<Result<ProductResponse>>
    fun getOils(productId: Int, name: String? = null): Flow<Result<OilResponse>>
    fun getPDS(oilId: Int): Flow<Result<PdsResponse>>
    fun getPublicContact(): Flow<Result<PublicContactResponse>>
    fun getRegionalContact(regionCode: String): Flow<Result<RegionalContactResponse>>
    fun getLanguage(): Languages
    fun setLanguage(languages: Languages)
}
