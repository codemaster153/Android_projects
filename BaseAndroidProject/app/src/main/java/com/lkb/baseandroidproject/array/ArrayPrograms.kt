//rotation of array element
//Time complexity O(n)
//Space complexity O(n)
fun rotateArray(arr: Array<Int>, d: Int, size: Int): Array<Int> {
    return arr.sliceArray(IntRange(d, size - 1)) + arr.sliceArray(IntRange(0, d - 1))
}

//time complexity O(n*d)
//space complexityO(1)
fun rotateArray1(arr: Array<Int>, d: Int, size: Int): Array<Int> {
    var p = 1 //fist rotation
    //d = total rotation required
    while (p <= d) {
        var last = arr[0]
        for (i in 0..size - 2) {
            arr[i] = arr[i + 1]
        }
        arr[size - 1] = last
        p++ // increase the rotation value
    }
    return arr
}

fun swap(arr: Array<Int>, i: Int, j: Int): Array<Int> {
    var temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
    return arr
}

fun partition(arr: Array<Int>, low: Int, high: Int): Int {
    var pivot = arr[high]
    var i = (low - 1)
    for (j in low until high) {
        if (arr[j] < pivot) {
            i++
            swap(arr, i, j)
        }
    }
    swap(arr, i + 1, high)
    return (i + 1)
}

fun quickSortLocal(arr: Array<Int>, low: Int, high: Int): Array<Int> {
    if (low < high) {
        var pi = partition(arr, low, high)

        quickSortLocal(arr, low, pi - 1)
        quickSortLocal(arr, pi + 1, high)
    }
    return arr
}


