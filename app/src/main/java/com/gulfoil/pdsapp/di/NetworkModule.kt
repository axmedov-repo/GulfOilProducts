package com.gulfoil.pdsapp.di

import com.gulfoil.pdsapp.data.remote.ApiClient
import com.gulfoil.pdsapp.data.remote.services.ApiService
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
    fun getApiService(): ApiService = ApiClient.retrofit.create(ApiService::class.java)
}
