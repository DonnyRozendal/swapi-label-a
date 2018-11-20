package com.example.donny.labela.data.network

import android.content.Context
import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.core.Failure
import com.example.donny.labela.data.core.networkInfo
import retrofit2.Call

class NetworkHandler(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}

fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform((response.body() ?: default)))
            false -> Either.Left(Failure.ServerError(response.code()))
        }
    } catch (exception: Throwable) {
        Either.Left(Failure.ServerError(0))
    }
}