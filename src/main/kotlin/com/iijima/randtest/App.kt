package com.iijima.randtest

import kotlin.random.Random

class App {}

fun calc(total: Int, wins_array: Array<Int>): Int {
    var denominator = total

    for (i in 0..(wins_array.size - 1)) {
        val wins = wins_array[i]
        val prob = wins.toDouble() / denominator.toDouble()
        val rand = Random.nextDouble()
        if (rand <= prob) {
            return i + 1
        }
        denominator -= wins
    }
    return 0
}

fun summary(total: Int, wins_array: Array<Int>) {
    val ar = Array(wins_array.size + 1, { 0 })
    for (i in 1..total) {
        ar[calc(total, wins_array)]++
    }

    println("---------------")
    println("総数: ${total}")
    println()
    println("| n等賞 | 本数 | 出た数 | 割合 | 確率 | 割合/確率(整合率) |")
    println("| --- | --- | --- | --- | --- | --- |")
    for (i in 0..(wins_array.size - 1)) {
        val wins = wins_array[i]
        val hits = ar[i + 1]
        val ratio = wins.toDouble() / total.toDouble()
        val prob = hits.toDouble() / total.toDouble()
        val ratio_per_prob = ratio/prob
        println("|${i + 1}等賞|${wins}|${hits}|${ratio.round()}|${prob.round()}|${ratio_per_prob.round()}|")
    }
}

fun Double.round(): Double {
    return Math.round(this * 10000).toDouble() / 10000.0
}

fun main(args: Array<String>) {
    val total1 = 1_000_000
    val wins_array1 = arrayOf(10_000, 100_000)
    summary(total1, wins_array1)
    summary(total1, wins_array1.reversedArray())

    val total2 = 1_000_000
    val wins_array2 = arrayOf(10_000, 20_000, 30_000, 40_000)
    summary(total2, wins_array2)
    summary(total2, wins_array2.reversedArray())
}
