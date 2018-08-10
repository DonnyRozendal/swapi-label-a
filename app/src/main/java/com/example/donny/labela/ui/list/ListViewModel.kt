package com.example.donny.labela.ui.list

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.example.donny.labela.data.models.Character
import com.example.donny.labela.data.repositories.CharacterRepository
import kotlinx.coroutines.experimental.launch

class ListViewModel(private val repository: CharacterRepository) : ViewModel() {
    var observable = MediatorLiveData<List<Character>>()

    fun fetchData() {
        launch {
            observable.addSource(repository.initFetch()) {
                observable.postValue(it)
            }
        }
    }

}