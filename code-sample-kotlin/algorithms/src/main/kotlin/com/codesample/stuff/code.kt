package com.codesample.stuff

import java.nio.file.Files
import java.nio.file.Paths


fun main() {
    val line = readLine()!!
    val split = line.split(" ")
    println("${split[0]}:${split[1]}:${split[2]}")
}
