package com.lkb.prinstarr.di

import android.app.Application
import android.content.SharedPreferences
import com.lkb.prinstarr.base_url
import com.lkb.prinstarr.domain.ConfigApiRepository
import com.lkb.prinstarr.domain.ConfigApiRepositoryImpl
import com.lkb.prinstarr.usecase.ConfigUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L
val networkModule = module {
    single { createService(get()) }

    single {createRetrofit(get(), base_url)  }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createConfigRepository(apiService: ApiService): ConfigApiRepository {
    return ConfigApiRepositoryImpl(apiService)
}

fun createConfigUseCase(postsRepository: ConfigApiRepository): ConfigUseCase {
    return ConfigUseCase(postsRepository)
}
