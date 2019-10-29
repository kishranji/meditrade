package com.medicine.app

import android.app.Application
import android.content.ContextWrapper


class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        APPPrefs.Builder().setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true).build()
    }
}