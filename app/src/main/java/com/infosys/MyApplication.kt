package com.infosys

import android.app.Application
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    companion object{
        val gson = Gson()
    }
}