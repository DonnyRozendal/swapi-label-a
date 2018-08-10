package com.example.donny.labela.app

import android.app.Application
import android.content.Context
import com.example.donny.labela.di.application.ApplicationComponent
import com.example.donny.labela.di.application.DaggerApplicationComponent
import com.example.donny.labela.di.data.DataModule

/**
 * Created by donnyrozendal on 09-08-18.
 */
class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                                    .builder()
                                    .dataModule(DataModule())
                                    .build()
    }

    companion object {
        fun get(context: Context): App {
            return context.applicationContext as App
        }
    }

}