package com.example.donny.labela.di.data

import com.example.donny.labela.data.repositories.CharacterRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by donnyrozendal on 09-08-18.
 */
@Module
class DataModule {

    @Singleton
    @Provides
    fun providesCharacterRepository(): CharacterRepository {
        return CharacterRepository()
    }

}