package com.example.donny.labela.ui.characterList

import androidx.lifecycle.MutableLiveData
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.data.usecases.GetAllCharacters
import com.example.donny.labela.ui.BaseViewModel


class CharacterListViewModel(private val getAllCharacters: GetAllCharacters) : BaseViewModel() {

    val characterList = MutableLiveData<List<Character>>()

    fun getAllCharacters() {
        getAllCharacters(GetAllCharacters.Params()) {
            it.either(::handleFailure, ::handleGetAllCharacters)
        }
    }

    private fun handleGetAllCharacters(success: List<Character>) {
        characterList.value = success
    }

}