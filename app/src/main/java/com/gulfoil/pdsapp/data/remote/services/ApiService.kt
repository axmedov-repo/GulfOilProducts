package com.gulfoil.pdsapp.data.remote.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{lan}/api/v1/")
    suspend fun getProducts(
        @Path("lan") lan: String
    ): Response<String>

    @GET("{lan}/api/v1/{id}/")
    suspend fun searchOil(
        @Path("lan") lan: String,
        @Path("id") productId: Int
    ): Response<String>

    @GET("{lan}/api/v1/{id}/")
    suspend fun getOils(
        @Path("lan") lan: String,
        @Path("id") productId: Int,
        @Query("name") name: String
    ): Response<String>

    @GET("{lan}/api/v1/item/{id}/")
    suspend fun getPDS(
        @Path("lan") lan: String,
        @Path("id") oilId: Int
    ): Response<String>

    @GET("{lan}/api/v1/contact-public/")
    suspend fun getPublicContact(
        @Path("lan") lan: String
    ): Response<String>

    @GET("{lan}/api/v1/contact/")
    suspend fun getRegionalContact(
        @Path("lan") lan: String,
        @Query("code") regionCode: String
    ): Response<String>

    @GET("{lan}/api/v1/advertising/")
    suspend fun getAds(
        @Path("lan") lan: String
    ): Response<String>
}
