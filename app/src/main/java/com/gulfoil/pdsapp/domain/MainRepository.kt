package com.gulfoil.pdsapp.domain

import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.AdResponseItem
import com.gulfoil.pdsapp.data.remote.responses.OilResponseItem
import com.gulfoil.pdsapp.data.remote.responses.PdsResponse
import com.gulfoil.pdsapp.data.remote.responses.ProductResponseItem
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponseItem
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponseItem
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProducts(): Flow<Result<List<ProductResponseItem>>>
    fun getOils(productId: Int, name: String? = null): Flow<Result<List<OilResponseItem>>>
    fun getPDS(oilId: Int): Flow<Result<PdsResponse>>
    fun getPublicContact(): Flow<Result<List<PublicContactResponseItem>>>
    fun getRegionalContact(regionCode: String): Flow<Result<List<RegionalContactResponseItem>>>
    fun getAds(): Flow<Result<List<AdResponseItem>>>
    fun getLanguage(): Languages
    fun setLanguage(languages: Languages)
}
