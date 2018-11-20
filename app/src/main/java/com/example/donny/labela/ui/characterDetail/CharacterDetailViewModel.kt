package com.example.donny.labela.ui.characterDetail

import androidx.lifecycle.MutableLiveData
import com.example.donny.labela.data.core.Either
import com.example.donny.labela.data.core.Failure
import com.example.donny.labela.data.models.Planet
import com.example.donny.labela.data.usecases.GetCharacterDetails
import com.example.donny.labela.ui.BaseViewModel

/**
 * Created by donnyrozendal on 24-08-18.
 */
class CharacterDetailViewModel(private val getCharacterDetails: GetCharacterDetails) : BaseViewModel() {

    val planet = MutableLiveData<Planet>()

    fun getPlanet(planetId: String) {
        val onResult: (Either<Failure, Planet>) -> Unit = {
            it.either(::handleFailure, ::handleGetCharacterDetails)
        }
        getCharacterDetails(GetCharacterDetails.Params(planetId), onResult)
    }

    private fun handleGetCharacterDetails(success: Planet) {
        planet.value = success
    }

}