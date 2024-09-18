package com.example.jetpackuistudy.learn_kotlin

class sample1 {




}

fun main() {
    println("Hello, world!!!")
    val test = arrayListOf(1,2,3)
    val filter = test.filterIndexed{index,s -> index!=0 && s !=3}
    println(filter)

    // string to char array and get the type
    val str = "Hello"
    val chars = str.toCharArray()
    println(chars::class.qualifiedName)
    println(filter::class.qualifiedName)


}