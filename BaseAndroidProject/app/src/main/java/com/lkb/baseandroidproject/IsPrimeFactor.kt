package com.lkb.baseandroidproject

// prime num 3,5, 7, 9, 11, 13,
//15   3*5
//21  7*3
//24  8*3
//8


//
fun primeTest(x: Int) {
var flag = 0
    for (i in 2 until x) {
        if ((x / i) % 2 != 0 && i * (x / i) == x) {
            if(isPrime(i) && isPrime(x/i)){
                println(" it is a prime with $i * ${x / i}")
                flag = 1
                break
            }

        }
    }
    if(flag==0){
        println("not a valid entry")
    }
}

fun isPrime(n: Int): Boolean {
    var isPrime = false
    var i: Int
    var m = 0
    var flag = 0
    m = n / 2
    if (n == 0 || n == 1) {
        isPrime = false
    } else {
        i = 2
        while (i <= m) {
            if (n % i == 0) {
                isPrime = false
                flag = 1
                break
            }
            i++
        }
        if (flag == 0) {
            isPrime = true
        }
    }
    return isPrime
}


fun main() {
    primeTest(33)
}
