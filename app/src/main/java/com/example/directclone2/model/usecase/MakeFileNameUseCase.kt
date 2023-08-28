package com.example.directclone2.model.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object MakeFileNameUseCase {

    private val formatter = SimpleDateFormat("yyyy_MM_dd_HHmmSS", Locale.getDefault())

    operator fun invoke(modelName: String, partNum: String, now: Date): String {
        val formattedNow = formatter.format(now)
        return "[Backup]_${modelName}_${partNum}_${formattedNow}"
    }
}