package com.lkb.baseandroidproject

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import kotlinx.serialization.json.Json

val client = HttpClient(Android) {
    install(JsonFeature) {
        serializer = GsonSerializer {
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = false
            }
        }
    }
}
