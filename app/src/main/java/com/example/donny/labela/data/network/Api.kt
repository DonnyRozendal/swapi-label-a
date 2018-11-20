package com.example.donny.labela.data.network

import com.example.donny.labela.data.models.CharacterList
import com.example.donny.labela.data.models.Planet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by donnyrozendal on 10-08-18.
 */
interface Api {

    @GET("people")
    fun fetchCharacterPage(@Query("page") page: String): Call<CharacterList>

    @GET("planets/{planet}")
    fun fetchPlanet(@Path("planet") planet: String): Call<Planet>

}