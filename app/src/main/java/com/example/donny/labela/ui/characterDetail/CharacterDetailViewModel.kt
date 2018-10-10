package com.example.donny.labela.ui.characterDetail

import android.arch.core.util.Function
import android.arch.lifecycle.*
import android.util.Log
import com.example.donny.labela.data.models.Planet
import com.example.donny.labela.data.repositories.CharacterDetailRepository

/**
 * Created by donnyrozendal on 24-08-18.
 */
class CharacterDetailViewModel(private val repository: CharacterDetailRepository) : ViewModel(), LifecycleObserver {

    val planetObservable = MutableLiveData<Planet>()

    fun getPlanet (planetId: String) {
        repository.initGetPlanet(planetId) {
            planetObservable.postValue(it)
        }
    }

}