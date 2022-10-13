package com.lkb.demo.di

import com.lkb.demo.data.api.APIService
import com.lkb.demo.data.repository.GetUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object MainModule {

    @Singleton
    @Provides
    fun provideApiService(): APIService {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(APIService::class.java)
    }

    @Provides
    fun provideUserRepository(apiService: APIService): GetUserRepository {
        return GetUserRepository(apiService)
    }
}