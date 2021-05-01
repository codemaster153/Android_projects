package com.lkb.baseandroidproject

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
class User constructor() : Parcelable {
    var uuid: String? = null
    var contact: String? = null
    var name: String? = null
    var address: String? = null
    var limit: Double? = null
    var transactions: HashMap<String,Transaction>? = null

    constructor(
        name: String?,
        address: String?,
        contact: String?,
        limit: Double?,
        transactions: HashMap<String,Transaction>?
    ) : this() {
        this.name = name
        this.address = address
        this.limit = limit
        this.transactions = transactions
        this.contact = contact
    }
}