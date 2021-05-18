package com.lkb.prinstarr

import androidx.annotation.Keep

@Keep
data class Transaction(
    val amount: Double = 0.0,
    val transactionDate: Long = 0,
    val isClosed: Boolean = false,
    val collections: String = "",
    val limitDays: Int = 0
)