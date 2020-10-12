package com.solar.retrofit

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkClient.let {
            it.CONNECTION_TIMEOUT = 10L
            it.WRITE_TIMEOUT = 30L
            it.READ_TIMEOUT = 30L
            it.HOST = "https://api.github.com/"
            it.IS_DEBUG = BuildConfig.DEBUG
        }
    }
}