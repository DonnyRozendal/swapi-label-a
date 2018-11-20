package com.example.donny.labela.data.usecases

import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.core.Failure
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.data.models.CharacterList
import com.example.donny.labela.utils.CharacterNameComparator
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class GetAllCharacters(private val getCharacterPage: GetCharacterPage) : UseCase<List<Character>, GetAllCharacters.Params>() {

    private var totalPages: Int = 0
    private val characterPages = mutableListOf<CharacterList>()
    private val missingPages = mutableListOf<Int>()
    private val jobs = mutableListOf<Deferred<Either<Failure, CharacterList>>>()

    override suspend fun run(params: Params): Either<Failure, List<Character>> {
        getFirstPage()
        createJobs()
        getOtherPages()
        return if (missingPages.isNotEmpty()) {
            Either.Left(PagesNotFound(missingPages))
        } else {
            Either.Right(convertPages(characterPages).sortedWith(CharacterNameComparator))
        }
    }

    private fun getFirstPage() {
        val page = runBlocking {
            GlobalScope.async {
                getCharacterPage.run(GetCharacterPage.Params(1))
            }.await()
        }
        page.either(::handleFailure, ::handleCharacterPage)
    }

    private fun createJobs() {
        for (i in 2..totalPages) {
            jobs.add(GlobalScope.async {
                getCharacterPage.run(GetCharacterPage.Params(i))
            })
        }
    }

    private fun getOtherPages() {
        runBlocking {
            for (job in jobs) {
                val result = job.await()
                result.either(::handleFailure, ::handleCharacterPage)
            }
        }
    }

    private fun handleCharacterPage(characterList: CharacterList) {
        characterPages.add(characterList)
        if (totalPages == 0) {
            totalPages = (characterList.count + 10 - 1) / 10
        }
    }

    private fun handleFailure(failure: Failure) {
        when (failure) {
            is GetCharacterPage.CharacterPageNotFound -> {
                missingPages.add(failure.pageNumber)
            }
        }
    }

    private fun convertPages(characterPages: MutableList<CharacterList>): List<Character> {
        return characterPages.map { x -> x.results.map { y -> y } }.flatten()
    }

    class Params

    class PagesNotFound(private val missingPages: List<Int>) : Failure.FeatureFailure()

}