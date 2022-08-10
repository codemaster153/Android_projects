package com.lkb.baseandroidproject.oops

fun main() {
    var a = A()
    a.printValue()
    var b: A = B()
    b.printValue()
    var c: A = C()
    c.printValue()
    var d: A = D()
    (d as B).printValue()
}

open class A {
    open fun printValue() {
        println("Its A class")
    }
}

open class B : A() {
    override fun printValue() {
        println("Its B class")
    }
}

open class C : B() {
    override fun printValue() {
        println("Its C class")
    }
}

class D : C() {
    fun printValue(msg: String) {
        println("Its D class $msg")
    }

    override fun printValue() {
        super.printValue()
        println("Its D class")
    }
}