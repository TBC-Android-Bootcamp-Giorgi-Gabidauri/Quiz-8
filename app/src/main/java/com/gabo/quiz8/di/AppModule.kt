package com.gabo.quiz8.di

import com.gabo.quiz8.constants.BASE_URL
import com.gabo.quiz8.data.global.api.PurchaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoursesApi(retrofit: Retrofit): PurchaseApi {
        return retrofit.create(PurchaseApi::class.java)
    }
}
