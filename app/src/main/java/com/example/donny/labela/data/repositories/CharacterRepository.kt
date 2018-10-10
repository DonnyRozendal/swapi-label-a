package com.example.donny.labela.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.donny.labela.data.api.Api
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.data.models.CharacterList
import com.example.donny.labela.utils.CharacterNameComparator
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch


class CharacterRepository(private val api: Api) : BaseRepository {
    val sortedCharacters = MutableLiveData<Result<List<Character>>>()
    var fetched = false
    private val characterPages = mutableListOf<CharacterList>()
    private val jsonStartPage: Int = 1
    private var amountPages: Double = 0.0
    private var totalPages: Int = 0

    fun initGetCharacters() {
        getCharacters(jsonStartPage)
    }

//    private fun getCharacters(number: Int) {
//        launch {
//            println("Attempting to fetch JSON page $number")
//
//            // Get list
//            try {
//                val response = api.fetchCharacterPage(number.toString()).execute()
//
//                if (response.isSuccessful) {
//                    val list = response.body()!!
//                    characterPages.add(list)
//
//                    // Post progress
//                    amountPages = Math.ceil(list.count / 10.0)
//                    progress.postValue((number / amountPages * 100).toInt())
//
//                    // Recursive statement that keeps fetching a json, until there is no next page
//                    if (!list.next.isNullOrEmpty()) {
//                        getCharacters(++jsonStartPage)
//                    } else {
//                        fetched = true
//                        val characters = convertPages(characterPages)
//                        val sortedCharacters = characters.sortedWith(CharacterNameComparator)
//                        this@CharacterRepository.sortedCharacters.postValue(Result(error = null, data = sortedCharacters))
//                    }
//
//                } else {
//                    val errorMessage = try {
//                        JSONObject(response.errorBody()?.string()).getString("detail")
//                    } catch (e: Exception) {
//                        e.message ?: "Error parsing error JSON"
//                    }
//                    this@CharacterRepository.sortedCharacters.postValue(Result(error = "API Exception: $errorMessage", data = null))
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                this@CharacterRepository.sortedCharacters.postValue(Result(error = "No internet", data = null))
//            }
//        }
//    }

    private fun convertPages(characterPages: MutableList<CharacterList>): List<Character> {
        return characterPages.map { it.results.map { it } }.flatten()
    }

    private fun getCharacters(pageNumber: Int) {
        GlobalScope.launch {
            println("Attempting to fetch JSON page $pageNumber")

            val response = api.fetchCharacterPage(pageNumber.toString()).execute()
            val list = response.body()!!
            characterPages.add(list)
            if (pageNumber == 1) {
                totalPages = (list.count + 10 - 1) / 10
                for (i in 2..totalPages) {
                    getCharacters(i)
                }
            }
            if (characterPages.size == totalPages) {
                fetched = true
                val characters = convertPages(characterPages)
                val sortedCharacters = characters.sortedWith(CharacterNameComparator)
                this@CharacterRepository.sortedCharacters.postValue(Result(sortedCharacters, null))
            }
        }
    }

}


data class Result<T>(val data: T?, val error: String?)

