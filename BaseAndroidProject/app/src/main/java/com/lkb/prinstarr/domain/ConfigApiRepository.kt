package com.lkb.prinstarr.domain

interface ConfigApiRepository {
    suspend fun getConfig(url:String) :LocalConfig
}