package com.example.donny.labela.ui.list.di

import com.example.donny.labela.ui.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @Provides
    fun providesListPresenter(): ListPresenter {
        return ListPresenter()
    }

}