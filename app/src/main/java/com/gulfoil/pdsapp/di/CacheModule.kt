package com.gulfoil.pdsapp.di

import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {
    @Provides
    @Singleton
    fun getLocalStorage(): LocalStorage = LocalStorage()

    @Provides
    @Singleton
    fun getGson(): Gson = Gson()
}
