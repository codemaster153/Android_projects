package com.lkb.baseandroidproject

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkService {

    suspend fun getData1(): Flow<String> {
        delay(5000)
        return flow<String> { emit("Data1") }
    }

    suspend fun getData2(): Flow<String> {
        delay(7000)
        return flow<String> { emit("Data2") }
    }


    companion object {
        fun getInstance(): NetworkService {
            return NetworkService()
        }
    }
}