package com.example.directclone2.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Profile::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProfileDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var Instance: ProfileDatabase? = null
        fun getDatabase(context: Context): ProfileDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ProfileDatabase::class.java, "profile_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}