package com.lkb.baseandroidproject

import com.google.firebase.database.IgnoreExtraProperties

//@IgnoreExtraProperties
//data class User(val name:String, val address: String, val limit:Double, val transactions: List<Transaction>?)

//class User{
//    var name:String = name
//    var limit = 0.0
//    lateinit var transactions: List<Transaction>
//    constructor()
//    constructor(val name:String,address: String,limit:Double,transactions: List<Transaction>?)
//
//}

@IgnoreExtraProperties
class User {
    var name: String? = null
    var address: String? = null
    var limit: Double? = null
    var transactions: List<Transaction>? = null

    constructor() {}
    constructor(name: String?, address: String?, limit: Double?, transactions: List<Transaction>?) {
        this.name = name
        this.address = address
        this.limit = limit
        this.transactions = transactions
    }
}