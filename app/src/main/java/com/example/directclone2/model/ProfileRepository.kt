package com.example.directclone2.model

import com.example.directclone2.model.data.toExternal
import com.example.directclone2.model.data.toLocal
import com.example.directclone2.viewmodel.ProfileUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

@ExperimentalCoroutinesApi
@FlowPreview
class ProfileRepository (
    private val localDataSource: ProfileDiskDataSource,
    //private val dispatcher: CoroutineDispatcher,
    //private val scope: CoroutineScope,
) {
    fun observeAll() : Flow<ProfileUiState> {
        return localDataSource.getProfile().map {
            it.toExternal()
        }
    }

    fun updateBluetooth(bluetooth: Boolean) {
        localDataSource.updateBluetooth(
            ConvertorUtil.booleanToOnOrOff(bluetooth))
    }

    fun updateNfc(nfc: Boolean) {
        localDataSource.updateNfc(
            ConvertorUtil.booleanToOnOrOff(nfc))
    }

    suspend fun create(profileUiState: ProfileUiState) {
        localDataSource.setProfile(profileUiState.toLocal())
    }

    private fun createTaskId() : String {
        return UUID.randomUUID().toString()
    }

    /*
    suspend fun setProfile(key: String, value: String) {
        val localProfile = localDataSource.getProfile().first()
        val profile = localProfile.update(key, value)
        localDataSource.setProfile(profile)
    }
     */

    fun delete() = localDataSource.delete()
}