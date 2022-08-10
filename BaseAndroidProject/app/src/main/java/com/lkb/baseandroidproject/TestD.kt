package com.lkb.baseandroidproject

//arr[1,19,3,2,4,16,6,10]  // n

fun main() {
sortArray(arrayOf(1,19,3,2,4,16,6,10),3).forEach(::println)
}
fun sortArray(arr: Array<Int>, n: Int): Array<Int> {
    for (i in n until arr.size) {
        for (j in n until arr.size) {
            if (arr[i] < arr[j]) {
                swap(arr, i, j)
            }
        }
    }
    return arr
}


//fun partition(arr:IntArray):Int{
//
//
//
//}



fun swap(arr:Array<Int>, i:Int, j:Int) {
    var temp = arr[i]
     arr[i]=arr[j]
    arr[j]=temp
}



