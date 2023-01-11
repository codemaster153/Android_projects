package com.lkb.baseandroidproject

sealed class QuizState<out T : Any> {
    data class Correct<out T : Any>(val data: T?) : QuizState<T>()
    data class Incorrect<out T : Any>(val data: T?) : QuizState<T>()
    data class Initial<out T : Any>(val data: T?) : QuizState<T>()
    object End : QuizState<Nothing>()
}

val <T> T.exhaustive: T
    get() = this


