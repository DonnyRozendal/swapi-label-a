package com.example.donny.labela.ui.list.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.donny.labela.data.repositories.CharacterRepository
import com.example.donny.labela.ui.list.ListViewModel

class ListViewModelFactory(private val repository: CharacterRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}