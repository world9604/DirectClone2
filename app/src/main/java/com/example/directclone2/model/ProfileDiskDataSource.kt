package com.example.directclone2.model

import android.util.Log
import com.example.directclone2.model.data.LocalFile
import com.example.directclone2.model.data.LocalProfile
import com.google.gson.Gson
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import java.util.Date


class ProfileDiskDataSource(private val jsonFile: File) {
    companion object {
        const val TAG = "ProfileDiskDataSource"
        @Volatile
        private var instance: ProfileDiskDataSource? = null
        fun getInstance(jsonFile: File) =
            instance ?: synchronized(ProfileDiskDataSource::class.java) {
                instance ?: ProfileDiskDataSource(jsonFile).also {
                    instance = it
                }
            }
    }

    private val accessMutex = Mutex()
    private var localFile = LocalFile(
        file = jsonFile,
        createdDate = Date(),
        password = ""
    )

    /*
    fun observeAll(): Flow<LocalBattery> = flow {
        val profile = getProfileFromFile()
        Log.d(TAG, "Profile in ProfileDiskDataSource.getProfile() : $profile")
        emit(profile.localBattery)
    }

    private fun getProfileFromFile(): LocalProfile {
        return if (jsonFile.exists()) {
            val jsonData = jsonFile.readText()
            Gson().fromJson(jsonData, LocalProfile::class.java)
        } else {
            throw FileNotFoundException("FileNotFoundException : ${jsonFile.absolutePath}")
        }
    }
    */

    suspend fun upsert(profile: LocalProfile) = accessMutex.withLock {
        Log.d(TAG, "Profile in ProfileDiskDataSource.setProfile() : ${Gson().toJson(profile)}")
        localFile.file.writeText(Gson().toJson(profile))
    }

    suspend fun delete() = accessMutex.withLock {
        localFile.file?.let {
            it.delete()
        }
    }

    suspend fun getFile(): File = accessMutex.withLock {
        return localFile.file
    }

    suspend fun create(fileName: String, password: String, createdDate: Date)
    = accessMutex.withLock {
        val destFile = File(jsonFile.parentFile, fileName)
        jsonFile.renameTo(destFile)
        localFile = LocalFile(
            file = jsonFile,
            createdDate = createdDate,
            password = password
        )
    }

    fun getParentFileName(): String = localFile.file.parent
}