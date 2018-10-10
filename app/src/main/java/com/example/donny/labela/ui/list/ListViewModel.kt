package com.example.donny.labela.ui.list

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.data.repositories.CharacterRepository
import com.example.donny.labela.data.repositories.Result


class ListViewModel(private val repository: CharacterRepository) : ViewModel() {

    val charObservable = MediatorLiveData<Result<List<Character>>>()

    fun getAllCharacters() {
        charObservable.removeSource(repository.sortedCharacters)
        charObservable.addSource(repository.sortedCharacters) {
            if (it?.error != null) {
                charObservable.postValue(Result(it.data, it.error))
            } else {
                it?.data?.let { list ->
                    charObservable.postValue(Result(list, it.error))
                }
            }
        }
        if (!repository.fetched) repository.initGetCharacters()
    }

}