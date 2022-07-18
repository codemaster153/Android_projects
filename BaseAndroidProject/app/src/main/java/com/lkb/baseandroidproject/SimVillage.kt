package com.lkb.baseandroidproject


fun main() {
    runSimulation("Lalit", ::printConstructionCost) { playerName, building ->
        val currentYear = 2022
        println("Adding building $building")
        "Welcome to SimVillage. $playerName! (copyright $currentYear )"
    }
}

inline fun runSimulation(
    playerName: String,
    costPrinter: (Int) -> Unit,
    greetingFunction: (String, Int) -> String
) {
    val buildingNumber = (1..7).shuffled().last()
    printConstructionCost(buildingNumber)
    println(greetingFunction(playerName, buildingNumber))
}

fun printConstructionCost(buildingNum: Int) {
    val constructionCost = 500
    println("construction cost of the buildi is ${constructionCost * buildingNum}")
}