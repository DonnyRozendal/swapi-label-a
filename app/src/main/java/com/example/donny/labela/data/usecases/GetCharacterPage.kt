package com.example.donny.labela.data.usecases

import android.util.Log
import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.core.Failure
import com.example.donny.labela.data.models.CharacterList
import com.example.donny.labela.data.repositories.CharacterListRepository

class GetCharacterPage(private val repository: CharacterListRepository) : UseCase<CharacterList, GetCharacterPage.Params>() {

    override suspend fun run(params: Params): Either<Failure, CharacterList> {
        Log.i("pageRun", "Getting page: ${params.pageNumber}")
        val result = repository.fetchCharacterPage(params.pageNumber)

        result.getLeft?.left?.let {
            if (it is Failure.ServerError && it.statusCode in 400..499) {
                return Either.Left(CharacterPageNotFound(params.pageNumber))
            }
        }
        return result
    }

    class Params(val pageNumber: Int)

    class CharacterPageNotFound(val pageNumber: Int) : Failure.FeatureFailure()

}