package com.example.donny.labela.di.application

import com.example.donny.labela.ui.characterDetail.CharacterDetailViewModel
import com.example.donny.labela.ui.list.ListViewModel
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

/**
 * Created by donnyrozendal on 24-08-18.
 */

val applicationModule: Module = module {
    viewModel<ListViewModel>()
    viewModel<CharacterDetailViewModel>()
}