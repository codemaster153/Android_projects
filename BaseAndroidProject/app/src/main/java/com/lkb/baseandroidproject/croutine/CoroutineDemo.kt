package com.lkb.baseandroidproject.croutine

import kotlinx.coroutines.*

fun main() = runBlocking {
    //withContext(){
    val job = CoroutineScope(Dispatchers.IO).launch {
        println("Hello")
        printThreadName()


    }
    job.join()
    //}
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