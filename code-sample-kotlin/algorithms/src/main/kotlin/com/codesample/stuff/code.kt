package com.codesample.stuff

import java.util.*


fun main() {
    val line = readLine()!!
    val split = line.split(" ")
    println("${split[0]}:${split[1]}:${split[2]}")
    val uuid = UUID.randomUUID()

}
