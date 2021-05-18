package com.lkb.prinstarr.domain

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Keep
data class LocalConfig(val shouldRun: Boolean, val appVersion: String, val dbPath: String)
