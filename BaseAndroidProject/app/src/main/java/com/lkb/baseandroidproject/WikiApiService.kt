package com.lkb.baseandroidproject

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.reactivex.Observable

//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Query
//import java.util.*

interface WikiApiService {
    //    @GET("api.php")
//    fun hitCountCheck(
//        @Query("action") action: String,
//        @Query("format") format: String,
//        @Query("list") list: String,
//        @Query("srsearch") srsearch: String
//    ): Observable<WikiModel.Result>
//
//
//    companion object {
//        fun create(): WikiApiService {
//            val retrofit = Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://en.wikipedia.org/w/")
//                .build()
//
//            return retrofit.create(WikiApiService::class.java)
//        }
//    }
    suspend fun getHitCount(txt: String): Observable<WikiModel.Result>

    companion object {
        fun create(): WikiApiService {
            return WikiApiServiceImpl(client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer(
                        json = kotlinx.serialization.json.Json {
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                    )
                }
            })
        }
    }
}