package com.magenic.kyleb.languageswitchingexample

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class LSEApplication: Application() {

    companion object {
        lateinit var preferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()

        preferences = getSharedPreferences("LWE_Preferences", Context.MODE_PRIVATE)
    }

}