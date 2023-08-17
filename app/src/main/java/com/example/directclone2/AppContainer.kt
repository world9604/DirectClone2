package com.example.directclone2

import com.example.directclone2.model.ProfileDiskDataSource
import com.example.directclone2.model.ProfileRepository
import java.io.File


interface AppContainer {
    val profileRepository: ProfileRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "/storage/emulated/0/Profile.json"

    private val diskDataSource: ProfileDiskDataSource by lazy {
        ProfileDiskDataSource.getInstance(File(baseUrl))
    }

    override val profileRepository: ProfileRepository by lazy {
        ProfileRepository.getInstance(diskDataSource)
    }
}
