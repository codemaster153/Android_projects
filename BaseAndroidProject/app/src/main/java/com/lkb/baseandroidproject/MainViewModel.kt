package com.lkb.baseandroidproject

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable

class MainViewModel : ViewModel() {

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    fun callHitCount(searchString:String):Observable<WikiModel.Result>{
        return wikiApiService.hitCountCheck("query", "json", "search", searchString)
    }

}