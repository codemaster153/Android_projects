package com.lkb.prinstarr.di

import com.lkb.prinstarr.domain.LocalConfig
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getConfig(@Url url:String): LocalConfig

}
