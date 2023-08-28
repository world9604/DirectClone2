package com.example.directclone2.model.usecase

import android.util.Log
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.model.data.Profile
import com.google.gson.Gson
import java.io.File
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CreateFileUseCase {

    private val gson = Gson()
    operator fun invoke(profile: Profile): Boolean {
        profile.backupApps.forEach {
            val fromFile = File(it.data)
            val data = fromFile.readText()
            Log.d(ProfileRepository.TAG, "data in createBackupFile: $data")
            val toFile = File("/storage/emulated/0/DirectClone/${it.appName}.txt")
            toFile.writeText(data)
        }
        val file = File(profile.filePath + profile.fileName)
        return try {
            file.writeText(gson.toJson(profile))
            true
        } catch (e: FileNotFoundException) {
            false
        }
    }
}