package com.gulfoil.pdsapp.domain.main

import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.data.remote.responses.product.AdResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.OilResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.PdsResponse
import com.gulfoil.pdsapp.data.remote.responses.product.ProductResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.PublicContactResponseItem
import com.gulfoil.pdsapp.data.remote.responses.product.RegionalContactResponseItem
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
