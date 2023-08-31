package com.example.directclone2

import android.app.Application
import com.example.directclone2.model.AppContainer
import com.example.directclone2.model.DefaultAppContainer
import device.lib.enterprise.config.data.entity.EnrollmentEntity

class DirectCloneApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}