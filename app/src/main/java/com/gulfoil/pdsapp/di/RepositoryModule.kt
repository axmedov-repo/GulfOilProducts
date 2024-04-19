package com.gulfoil.pdsapp.di

import com.gulfoil.pdsapp.domain.key.KeyRepository
import com.gulfoil.pdsapp.domain.key.KeyRepositoryImpl
import com.gulfoil.pdsapp.domain.main.MainRepository
import com.gulfoil.pdsapp.domain.main.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun getMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    fun getKeyRepository(mainRepositoryImpl: KeyRepositoryImpl): KeyRepository
}
