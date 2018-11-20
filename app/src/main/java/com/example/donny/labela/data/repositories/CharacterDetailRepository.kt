package com.example.donny.labela.data.repositories

import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.core.Either.Left
import com.example.donny.labela.data.core.Failure
import com.example.donny.labela.data.models.Planet
import com.example.donny.labela.data.network.Api
import com.example.donny.labela.data.network.NetworkHandler
import com.example.donny.labela.data.network.request

/**
 * Created by donnyrozendal on 24-08-18.
 */
interface CharacterDetailRepository {

    fun fetchPlanet(planetId: String): Either<Failure, Planet>

    class Network(private val networkHandler: NetworkHandler,
                  private val api: Api) : CharacterDetailRepository {

        override fun fetchPlanet(planetId: String): Either<Failure, Planet> {
            return when (networkHandler.isConnected) {
                true -> request(api.fetchPlanet(planetId), { it }, Planet.empty())
                false, null -> Left(Failure.NetworkConnection())
            }
        }

    }

}