package com.example.donny.labela.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.donny.labela.data.repositories.BaseRepository
import com.example.donny.labela.data.repositories.CharacterDetailRepository
import com.example.donny.labela.data.repositories.CharacterRepository
import com.example.donny.labela.ui.characterDetail.CharacterDetailViewModel
import com.example.donny.labela.ui.list.ListViewModel

class ViewModelFactory(private val repository: BaseRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repository as CharacterRepository) as T
        }
        if (modelClass.isAssignableFrom(CharacterDetailViewModel::class.java)) {
            return CharacterDetailViewModel(repository as CharacterDetailRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}