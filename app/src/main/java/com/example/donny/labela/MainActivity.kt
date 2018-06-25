package com.example.donny.labela

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var jsonStartPage: Int = 1
    var characterPages = mutableListOf<CharacterList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchJson(jsonStartPage)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchJson(number: Int) {
        println("Attempting to fetch JSON page $number")

        val url = "https://swapi.co/api/people/?page=$number"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val list = gson.fromJson(body, CharacterList::class.java)
                characterPages.add(list)
                println(characterPages.size)

                // Recursive statement that keeps fetching a json, until there is no next page
                if (list.next != null) {
                    fetchJson(++jsonStartPage)
                } else {
                    val characters = convertPages(characterPages)
                    val sortedCharacters = characters.sortedWith(CharacterNameComparator)
                    runOnUiThread {
                        recyclerView_main.adapter = MainAdapter(sortedCharacters)
                    }
                }
            }
            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })
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