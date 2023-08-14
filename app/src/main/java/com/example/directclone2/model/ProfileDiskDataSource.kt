package com.example.directclone2.model

import android.util.Log
import com.example.directclone2.model.data.LocalProfile
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileNotFoundException


class ProfileDiskDataSource(private val jsonFile: File) {

    companion object {
        const val TAG = "ProfileDao"
    }

    fun getProfile(): Flow<LocalProfile> = flow {
        val profile = getProfileFromFile()
        Log.d(TAG, "Profile in ProfileDao.getProfile() : $profile")
        emit(profile)
    }

    private fun getProfileFromFile(): LocalProfile {
        return if (jsonFile.exists()) {
            val jsonData = jsonFile.readText()
            Gson().fromJson(jsonData, LocalProfile::class.java)
        } else {
            throw FileNotFoundException("FileNotFoundException : ${jsonFile.absolutePath}")
        }
    }

    fun setProfile(profile: LocalProfile) {
        Log.d(TAG, "Profile in ProfileDao.setProfile() : ${Gson().toJson(profile)}")
        jsonFile.writeText(Gson().toJson(profile))
    }

    fun updateBluetooth(value: String) {
        val profile = getProfileFromFile()
        profile.bluetooth = value
        Log.d(TAG, "Profile in ProfileDao.getProfile() : $profile")
        jsonFile.writeText(Gson().toJson(profile))
    }

    fun updateNfc(value: String) {
        val profile = getProfileFromFile()
        profile.nfc = value
        Log.d(TAG, "Profile in ProfileDao.getProfile() : $profile")
        jsonFile.writeText(Gson().toJson(profile))
    }

    fun delete() {
        jsonFile?.let { delete() }
    }
}