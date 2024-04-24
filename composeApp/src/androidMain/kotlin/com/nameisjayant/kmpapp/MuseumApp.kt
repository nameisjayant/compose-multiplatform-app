package com.nameisjayant.kmpapp

import android.app.Application
import com.nameisjayant.kmpapp.mesume.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
