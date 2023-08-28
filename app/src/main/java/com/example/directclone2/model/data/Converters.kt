package com.example.directclone2.model.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromBackupAppList(value: List<LocalBackupApp>): String {
        val gson = Gson()
        val type = object : TypeToken<List<LocalBackupApp>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toBackupAppList(value: String): List<LocalBackupApp> {
        val gson = Gson()
        val type = object : TypeToken<List<LocalBackupApp>>() {}.type
        return gson.fromJson(value, type)
    }
}