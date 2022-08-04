package com.lkb.baseandroidproject.scopef


fun main() {
    val nullVar = 4
    println("variable is $nullVar")
    val someValue  = nullVar?.run{
        println("inside run method scope")
        5
    }
    println(someValue)
}