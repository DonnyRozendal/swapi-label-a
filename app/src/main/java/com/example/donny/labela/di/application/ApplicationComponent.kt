package com.example.donny.labela.di.application

import com.example.donny.labela.data.repositories.CharacterRepository
import com.example.donny.labela.di.data.DataModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by donnyrozendal on 09-08-18.
 */
@Singleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {

    fun characterRepository(): CharacterRepository

}