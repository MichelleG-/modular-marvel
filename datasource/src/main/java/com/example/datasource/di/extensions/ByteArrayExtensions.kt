package com.example.datasource.di.extensions

fun ByteArray.toHex() = joinToString("") {
    "%02x".format(it)
}
