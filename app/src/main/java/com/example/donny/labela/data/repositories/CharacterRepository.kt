package com.example.donny.labela.data.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.data.models.CharacterList
import com.example.donny.labela.utils.CharacterNameComparator
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request

class CharacterRepository {
    val sortedCharacters = MutableLiveData<List<Character>>()
    var fetched = false
    private var characterPages = mutableListOf<CharacterList>()
    private var jsonStartPage: Int = 1

    fun initFetch(): LiveData<List<Character>> {
        if (!fetched) {
            fetchCharacters(jsonStartPage)
        }
        return sortedCharacters
    }

    private fun fetchCharacters(number: Int) {
        println("Attempting to fetch JSON page $number")

        val url = "https://swapi.co/api/people/?page=$number"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        // Synchronous call with coroutine
        val response = client.newCall(request).execute()
        val body = response.body()?.string()
        val gson = GsonBuilder().create()
        val list = gson.fromJson(body, CharacterList::class.java)
        characterPages.add(list)

        // Recursive statement that keeps fetching a json, until there is no next page
        if (!list.next.isNullOrEmpty()) {
            fetchCharacters(++jsonStartPage)
        } else {
            fetched = true
            val characters = convertPages(characterPages)
            val sortedCharacters = characters.sortedWith(CharacterNameComparator)
            this.sortedCharacters.postValue(sortedCharacters)
        }

    }

    private fun convertPages(characterPages: MutableList<CharacterList>): MutableList<Character> {
        val characters = mutableListOf<Character>()
        for (page in characterPages) {
            for (character in page.results) {
                characters.add(character)
            }
        }
        return characters
    }

}