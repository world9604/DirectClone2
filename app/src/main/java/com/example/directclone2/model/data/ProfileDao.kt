package com.example.directclone2.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile: Profile)

    @Update
    suspend fun update(profile: Profile)

    @Delete
    suspend fun delete(profile: Profile)

    @Query("SELECT * from profiles WHERE id = :id")
    suspend fun getProfile(id: String): Profile?

    @Query("SELECT * from profiles ORDER BY id ASC")
    fun getAllProfiles(): Flow<List<Profile>>

    @Query("SELECT * FROM profiles WHERE isFileCreated = 0")
    fun observeNotCreatedFile(): Flow<Profile>

    @Query("SELECT id FROM profiles WHERE isFileCreated = 0 LIMIT 1")
    fun observeWorkingProfileId(): Flow<String>

    @Query("UPDATE profiles SET isFileCreated = 1 WHERE id = :id")
    suspend fun updateIsCreated(id: String)
}