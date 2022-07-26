package com.lkb.baseandroidproject.croutine

import kotlinx.coroutines.*

fun main() = runBlocking { //5,6, 7, 6, 6,
    scopeFunctionTest()
}

fun scopeFunctionTest() {
    val x = 5
    val y = with(x) {
        println(this) //prints value of x =5
        inc() //value incremented and returned from here and got assigned to y =6
    }
    println(y) // prints value of y which is = 6
    val z = y.apply {
        println(inc())
        this.inc()
    } // value of y incremented which is = 7 and gets printed - apply does not return the lambda value so z = y = 6
    val a = x.run {
        println(inc())
        this.inc()
    } // get incremented value of x -> 5+1 and printed 6 and value of
    val b = x?.let {
        println(it.inc())
        it.inc()
    }
    val c = x?.also {
        println(it.inc())
        it.inc()
    }
    println(z)
    println(a)
    println(b)
    println(c)
}


suspend fun printThreadName() =
    withContext(Dispatchers.IO) {
        println("the current thread ${Thread.currentThread()}")
        delay(1000L)

        //}

    }


suspend fun bigTask() {
    printThreadName()
    delay(2000L)
}

suspend fun getValues(): List<Int> {
    delay(1000L)
    return (1..10).map { i -> i * i }
}


suspend fun getPosts(): List<Posts> {
    delay(2000L)
    return listOf<Posts>(
        Posts("India wins", "India wins the T20 world cup"),
        Posts("It will rain today", "Heavy rain expected this week on south states of India"),
        Posts("Modi visits Varanasi", "Modi visiting varanasi on 30th of this month")
    )
}

data class Posts(val title: String, val messages: String)