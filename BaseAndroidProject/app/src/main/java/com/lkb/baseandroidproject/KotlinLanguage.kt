package com.lkb.baseandroidproject

fun main() {
    var list = listOf(1,2,3,4,5,6)
  //list.withIndex().forEach { iv-> println("index is ${iv.index} and value is ${iv.value}") }
    println(::add.invoke(4,5))
}

fun add(a: Int, b: Int): Int {
    return a + b
}