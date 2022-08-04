package com.lkb.baseandroidproject

import com.lkb.baseandroidproject.javademo.JavaDemo

fun main() {
    //JavaDemo.helloWorld()
    val shape = Shape("sqare","this is a square")

}

class Shape(val name: String) {
    constructor(name: String, desc: String) : this(name)
}
