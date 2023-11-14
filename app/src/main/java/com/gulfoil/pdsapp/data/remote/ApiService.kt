package com.gulfoil.pdsapp.data.remote

import com.gulfoil.pdsapp.data.entities.ProductData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{lan}/api/v1/")
    suspend fun getProducts(
        @Path("lan") lan: String
    ) : Response<ProductData>
}