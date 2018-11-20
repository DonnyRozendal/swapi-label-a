package com.example.donny.labela.app

import android.app.Application
import com.example.donny.labela.di.koinModules
import org.koin.android.ext.android.startKoin

/**
 * Created by donnyrozendal on 09-08-18.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, koinModules)
    }

}