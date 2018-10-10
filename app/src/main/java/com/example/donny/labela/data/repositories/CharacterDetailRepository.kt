package com.example.donny.labela.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.donny.labela.data.api.Api
import com.example.donny.labela.data.models.Planet
import kotlinx.coroutines.experimental.launch

/**
 * Created by donnyrozendal on 24-08-18.
 */
class CharacterDetailRepository(private val api: Api) {

    fun initGetPlanet(planetId: String, callback: (planet: Planet) -> Unit)  {
        launch {
            api.fetchPlanet(planetId).execute().body()?.let {
                callback(it)
            }
        }
    }

}