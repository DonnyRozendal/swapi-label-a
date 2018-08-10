package com.example.donny.labela.ui.list.di

import com.example.donny.labela.di.activity.ActivityScope
import com.example.donny.labela.di.application.ApplicationComponent
import com.example.donny.labela.ui.list.ListActivity
import dagger.Component

@ActivityScope
@Component(modules = [ListModule::class], dependencies = [ApplicationComponent::class])
interface ListComponent {

    fun inject(activity: ListActivity)

}