package com.lkb.baseandroidproject

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User constructor() {
    var uuid: String? = null
    var contact: String? = null
    var name: String? = null
    var address: String? = null
    var limit: Double? = null
    var transactions: List<Transaction>? = null

    constructor(
        name: String?,
        address: String?,
        contact: String?,
        limit: Double?,
        transactions: List<Transaction>?
    ) : this() {
        this.name = name
        this.address = address
        this.limit = limit
        this.transactions = transactions
        this.contact = contact
    }
}