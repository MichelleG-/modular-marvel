package com.example.datasource.di.mapper

interface Mapper<F, T> {

    suspend fun map(from: F): T
}
