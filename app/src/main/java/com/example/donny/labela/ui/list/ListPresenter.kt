package com.example.donny.labela.ui.list

import com.example.donny.labela.data.models.Character
import com.example.donny.labela.data.models.CharacterList
import com.example.donny.labela.ui.base.BasePresenter
import com.example.donny.labela.utils.CharacterNameComparator
import com.google.gson.GsonBuilder
import okhttp3.*

class ListPresenter : BasePresenter<ListView>() {

    private var characterPages = mutableListOf<CharacterList>()
    private var jsonStartPage: Int = 1
    private var sortedCharacters = listOf<Character>()

    fun initFetch(): List<Character> {
        fetchCharacters(jsonStartPage)
        return sortedCharacters
    }

    private fun fetchCharacters(number: Int) {
        println("Attempting to fetch JSON page $number")

        val url = "https://swapi.co/api/people/?page=$number"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        // Synchronous call
        val response = client.newCall(request).execute()
        val body = response.body()?.string()
        val gson = GsonBuilder().create()
        val list = gson.fromJson(body, CharacterList::class.java)
        characterPages.add(list)
        println(characterPages.size)

        // Recursive statement that keeps fetching a json, until there is no next page
        if (list.next != null) {
            fetchCharacters(++jsonStartPage)
        } else {
            val characters = convertPages(characterPages)
            val sortedCharacters = characters.sortedWith(CharacterNameComparator)
            this.sortedCharacters = sortedCharacters
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