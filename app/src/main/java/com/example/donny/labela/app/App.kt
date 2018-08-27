package com.example.donny.labela.app

import android.app.Application
import com.example.donny.labela.data.api.networkModule
import com.example.donny.labela.di.application.applicationModule
import com.example.donny.labela.di.data.dataModule
import org.koin.android.ext.android.startKoin

/**
 * Created by donnyrozendal on 09-08-18.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule, applicationModule, dataModule))
    }

}