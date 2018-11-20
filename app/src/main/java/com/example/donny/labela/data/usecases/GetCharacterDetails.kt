package com.example.donny.labela.data.usecases

import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.core.Failure
import com.example.donny.labela.data.models.Planet
import com.example.donny.labela.data.repositories.CharacterDetailRepository

class GetCharacterDetails(private val repository: CharacterDetailRepository) : UseCase<Planet, GetCharacterDetails.Params>() {

    override suspend fun run(params: Params): Either<Failure, Planet> {
        val result = repository.fetchPlanet(params.planetId)

        result.getLeft?.left?.let {
            if (it is Failure.ServerError && it.statusCode in 400..499) {
                return Either.Left(CharacterDetailsNotFound())
            }
        }
        return result
    }

    class Params(val planetId: String)

    class CharacterDetailsNotFound : Failure.FeatureFailure()

}