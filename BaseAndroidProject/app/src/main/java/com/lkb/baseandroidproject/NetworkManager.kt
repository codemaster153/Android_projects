package com.lkb.baseandroidproject

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

val client = HttpClient() {
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = false
            }
        )
    }
}
