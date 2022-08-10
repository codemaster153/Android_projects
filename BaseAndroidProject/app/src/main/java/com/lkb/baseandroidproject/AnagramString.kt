//An anagram of a string is another string that contains the same characters,
// only the order of characters can be different.

fun main() {
    println(
        testAnagram("aba", "baa")
    )
}

fun testAnagram(s: String, t: String): Boolean {
    var sArr = s.toCharArray()
    var isAnagram = false
    for (i in s.indices) {
        for (j in s.indices) {
            if (i != j) {
                var temp = swap(sArr, i, j).concatToString()
                if (temp == t) {
                    isAnagram = true
                }
            }
        }
    }
    return isAnagram
}

fun swap(arr: CharArray, i: Int, k: Int): CharArray {
    var temp = arr[i]
    arr[i] = arr[k]
    arr[k] = temp
    return arr
}