package com.gulfoil.pdsapp.di

import com.gulfoil.pdsapp.data.remote.ApiClient
import com.gulfoil.pdsapp.data.remote.services.KeyService
import com.gulfoil.pdsapp.data.remote.services.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun getProductService(): ProductService =
        ApiClient.retrofitWithScalarsConverter.create(ProductService::class.java)

    @Provides
    @Singleton
    fun getKeyService(): KeyService =
        ApiClient.retrofitWithGsonConverter.create(KeyService::class.java)
}
