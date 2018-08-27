package com.example.donny.labela.data.api

import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by donnyrozendal on 24-08-18.
 */

val networkModule: Module = module {
    single { createRetrofit() }
    single { createWebService(get()) }
}

fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}

fun createWebService(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}