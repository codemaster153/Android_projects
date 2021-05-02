package com.lkb.prinstarr.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocalConfig (val shouldRun:Boolean, val appVersion:String, val dbPath:String)
