package com.lkb.prinstarr.domain

import com.lkb.prinstarr.di.ApiService

class ConfigApiRepositoryImpl(private val apiService: ApiService):ConfigApiRepository {
    override suspend fun getConfig(url:String) :LocalConfig{
        return apiService.getConfig(url)
    }
}