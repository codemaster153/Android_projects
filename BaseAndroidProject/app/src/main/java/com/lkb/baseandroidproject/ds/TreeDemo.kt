package com.lkb.baseandroidproject.ds

import java.util.*
import kotlin.collections.ArrayDeque

data class Node(var value: Int, var left: Node? = null, var right: Node? = null)

fun main() {
    var list = getNumbers()
    printLevelOrder(insertLevelOrder(list, 0))

}

private fun getNumbers(): List<Int> {
//    val scanner = Scanner(System.`in`)
//    println("Enter the Number of value want to scan:")
//    val length = scanner.nextInt()
//    println("Enter the Number one by one:")
//    var list = mutableListOf<Int>()
//    for (i in 0 until length) {
//        list.add(scanner.nextInt())
//    }
//    return list
    return listOf(1, 2, 3, 4, 5, 6, 7)
}

fun insertLevelOrder(list: List<Int>, i: Int): Node? {
    var root: Node? = null
    if (i < list.size) {
        root = Node(value = list[i])
        root.left = insertLevelOrder(list, 2 * i + 1)
        root.right = insertLevelOrder(list, 2 * i + 2)
    }
    return root
}

fun printLevelOrder(root: Node?) {
    var queue = ArrayDeque<Node>()
    root?.let { queue.addFirst(it) }
    while (true) {
        var nodeCount = queue.size
        if (nodeCount == 0)
            break
        while (nodeCount > 0) {
            val node = queue.last()
            print(" ${node.value} ")
            queue.removeLast()
            node.left?.let { queue.addFirst(it) }
            node.right?.let { queue.addFirst(it) }
            nodeCount--
        }
        println()
    }
}

fun printInorder(root: Node?) {
    if (root != null) {
        printInorder(root.left)
        print(root.value)
        printInorder(root.right)
    }
}

fun printPreOrder(root: Node?) {
    if (root != null) {
        print(root.value)
        printInorder(root.left)
        printInorder(root.right)
    }
}

fun printPostOrder(root: Node?) {
    if (root != null) {
        printInorder(root.left)
        printInorder(root.right)
        print(root.value)
    }
}

