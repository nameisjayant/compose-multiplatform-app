package com.nameisjayant.kmpapp

import android.app.Application
import com.nameisjayant.kmpapp.api.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
