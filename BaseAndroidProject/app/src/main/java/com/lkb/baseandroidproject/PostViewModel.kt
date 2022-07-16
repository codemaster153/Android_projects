package com.lkb.baseandroidproject

import ErrorState
import PostService
import RetrofitHelper
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class PostViewModel : BaseModel() {
    val posts = flow {
        val retrofit = RetrofitHelper.getInstance().create(PostService::class.java)
        val posts = retrofit.getPosts()
        emit(posts)
    }.flowOn(Dispatchers.IO).catch { _ ->
       viewModelScope.launch {
           error = flowOf("erro happen")
       }
    }
}