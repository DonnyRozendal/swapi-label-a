package com.example.donny.labela.di.data

import com.example.donny.labela.data.repositories.CharacterDetailRepository
import com.example.donny.labela.data.repositories.CharacterRepository
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * Created by donnyrozendal on 24-08-18.
 */

val dataModule: Module = module {
    single<CharacterRepository>()
    single<CharacterDetailRepository>()
}