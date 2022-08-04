package com.lkb.baseandroidproject.extensionf

class ExtensionDemo {
    val x = 5
}

fun ExtensionDemo.getValue(): Int {
    return x
}

fun main() {
    println(ExtensionDemo().getValue())
}