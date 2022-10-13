package com.lkb.demo.data.repository

import com.lkb.demo.data.api.APIService

class GetUserRepository(val apiService: APIService) {
    suspend fun getPost() = apiService.getPosts()
}