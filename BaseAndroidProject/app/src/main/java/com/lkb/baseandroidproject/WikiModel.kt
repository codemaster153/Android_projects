package com.lkb.baseandroidproject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
object WikiModel {
    @Serializable
    data class Result(@SerialName("query") val query: Query)

    @Serializable
    data class Query(@SerialName("searchinfo") val searchinfo: SearchInfo)

    @Serializable
    data class SearchInfo(@SerialName("totalhits") val totalhits: Int)
}