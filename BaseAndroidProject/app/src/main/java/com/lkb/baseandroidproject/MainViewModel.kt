package com.lkb.baseandroidproject

import androidx.lifecycle.ViewModel
import io.reactivex.Observable

class MainViewModel : ViewModel() {
private val client = WikiApiService.create()
    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    suspend fun callHitCount(searchString:String):Observable<WikiModel.Result>{
        return client.getHitCount(searchString)
    }

}