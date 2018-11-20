package com.example.donny.labela.di

import com.example.donny.labela.data.network.Api
import com.example.donny.labela.data.network.NetworkHandler
import com.example.donny.labela.data.repositories.CharacterDetailRepository
import com.example.donny.labela.data.repositories.CharacterListRepository
import com.example.donny.labela.data.usecases.GetAllCharacters
import com.example.donny.labela.data.usecases.GetCharacterDetails
import com.example.donny.labela.data.usecases.GetCharacterPage
import com.example.donny.labela.ui.characterDetail.CharacterDetailViewModel
import com.example.donny.labela.ui.characterList.CharacterListViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.koin.experimental.builder.single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val applicationModule: Module = module {
    viewModel<CharacterListViewModel>()
    viewModel<CharacterDetailViewModel>()
}

val useCaseModule: Module = module {
    single<GetCharacterDetails>()
    single<GetAllCharacters>()
    single<GetCharacterPage>()
}

val repositoryModule: Module = module {
    single<CharacterListRepository>{ CharacterListRepository.Network(get(), get()) }
    single<CharacterDetailRepository> { CharacterDetailRepository.Network(get(), get()) }
}

val networkModule: Module = module {
    single { createRetrofit() }
    single { createWebService(get()) }
    single { NetworkHandler(get()) }
}

val koinModules = listOf<Module>(applicationModule)
        .plus(useCaseModule)
        .plus(repositoryModule)
        .plus(networkModule)

fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}

fun createWebService(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}