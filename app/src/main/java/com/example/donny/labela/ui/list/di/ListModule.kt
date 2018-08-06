package com.example.donny.labela.ui.list.di

import com.example.donny.labela.data.repositories.CharacterRepository
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @Provides
    fun providesCharacterRepository(): CharacterRepository {
        return CharacterRepository()
    }

    @Provides
    fun providesViewModelFactory(repository: CharacterRepository): ListViewModelFactory {
        return ListViewModelFactory(repository)
    }

}