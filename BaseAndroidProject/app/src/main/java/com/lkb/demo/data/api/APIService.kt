package com.lkb.demo.data.api

import com.lkb.demo.data.model.FakeDTO
import retrofit2.http.GET

interface APIService {

    @GET("/posts")
    suspend fun getPosts(): List<FakeDTO>
}