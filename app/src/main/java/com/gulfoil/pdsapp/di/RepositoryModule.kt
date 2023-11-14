package com.gulfoil.pdsapp.di

import com.gulfoil.pdsapp.domain.MainRepository
import com.gulfoil.pdsapp.domain.MainRepositoryImpl
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
}
