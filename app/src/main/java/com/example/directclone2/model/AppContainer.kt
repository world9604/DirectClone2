package com.example.directclone2.model

import android.content.Context
import com.example.directclone2.model.data.ProfileDatabase


interface AppContainer {
    val profileRepository: ProfileRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val profileRepository: ProfileRepository by lazy {
        ProfileRepository.getInstance(ProfileDatabase.getDatabase(context).profileDao())
    }
}
