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
    suspend fun insert(localProfile: LocalProfile)

    @Update
    suspend fun update(localProfile: LocalProfile)

    @Delete
    suspend fun delete(localProfile: LocalProfile)

    @Query("SELECT * from profiles WHERE id = :id")
    suspend fun getProfile(id: String): LocalProfile?

    @Query("SELECT * from profiles ORDER BY id ASC")
    fun getAllProfiles(): Flow<List<LocalProfile>>

    @Query("SELECT * FROM profiles WHERE isFileCreated = 0")
    fun observeNotCreatedFile(): Flow<LocalProfile>

    @Query("SELECT id FROM profiles WHERE isFileCreated = 0 LIMIT 1")
    fun observeWorkingProfileId(): Flow<String>

    @Query("SELECT * FROM profiles WHERE isFileCreated = 1")
    fun observeFiles(): Flow<List<LocalProfile>>

    @Query("UPDATE profiles SET isFileCreated = 1 WHERE id = :id")
    suspend fun updateIsCreated(id: String)
}