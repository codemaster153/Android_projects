package com.lkb.baseandroidproject

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.reactivex.Observable

class WikiApiServiceImpl(private val client: HttpClient) : WikiApiService {
    override suspend fun getHitCount(txt: String): Observable<WikiModel.Result> {
        return Observable.just(client.get {
            url(HttpRoutes.GET_HIT_COUNT)
            parameter("action","query")
            parameter("format","json")
            parameter("list","search")
            parameter("srsearch",txt)
        })
    }
}