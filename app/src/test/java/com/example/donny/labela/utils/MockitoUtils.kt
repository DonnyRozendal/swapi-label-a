package com.example.donny.labela.utils

import org.mockito.Mockito

inline fun <reified T> mock() = Mockito.mock(T::class.java)
fun <T> any(): T = Mockito.any<T>()