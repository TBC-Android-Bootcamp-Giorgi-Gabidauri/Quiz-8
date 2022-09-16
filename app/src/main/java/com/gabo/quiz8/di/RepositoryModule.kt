package com.gabo.quiz8.di

import com.gabo.quiz8.data.repository.PurchaseRepositoryImpl
import com.gabo.quiz8.domain.repository.PurchaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(purchaseRepositoryImpl: PurchaseRepositoryImpl): PurchaseRepository
}
