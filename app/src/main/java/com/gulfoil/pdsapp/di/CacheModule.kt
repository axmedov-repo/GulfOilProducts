package com.gulfoil.pdsapp.di

import com.google.gson.Gson
import com.gulfoil.pdsapp.data.cache.KeyStorage
import com.gulfoil.pdsapp.data.cache.LocalStorage
import com.gulfoil.pdsapp.data.encryption.KeyManager
import com.gulfoil.pdsapp.data.encryption.KeyStoreManager
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
    fun getKeyStorage(): KeyStorage = KeyStorage()

    @Provides
    @Singleton
    fun getKeyManager(): KeyManager = KeyManager()

    @Provides
    @Singleton
    fun getGson(): Gson = Gson()

    @Provides
    @Singleton
    fun getKeyStoreManager(): KeyStoreManager =
        KeyStoreManager(keyCache = getKeyStorage(), keyManager = getKeyManager())
}
