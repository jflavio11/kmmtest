package com.jflavio.kmmtest

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}