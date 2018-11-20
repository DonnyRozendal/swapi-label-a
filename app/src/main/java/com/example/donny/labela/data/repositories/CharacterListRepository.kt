package com.example.donny.labela.data.repositories

import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.core.Failure
import com.example.donny.labela.data.models.CharacterList
import com.example.donny.labela.data.network.Api
import com.example.donny.labela.data.network.NetworkHandler
import com.example.donny.labela.data.network.request

interface CharacterListRepository {

    fun fetchCharacterPage(pageNumber: Int): Either<Failure, CharacterList>

    class Network(private val networkHandler: NetworkHandler,
                  private val api: Api) : CharacterListRepository {

        override fun fetchCharacterPage(pageNumber: Int): Either<Failure, CharacterList> {
            return when (networkHandler.isConnected) {
                true -> request(api.fetchCharacterPage(pageNumber.toString()), { it }, CharacterList.empty())
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

    }

}