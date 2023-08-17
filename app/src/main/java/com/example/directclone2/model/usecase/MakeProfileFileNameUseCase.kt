package com.example.directclone2.model.usecase

import java.text.SimpleDateFormat
import java.util.Date

object MakeProfileFileNameUseCase {

    operator fun invoke(modelName: String, partNum: String): String {
        val formattedNow = SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date())
        return "[Backup]_${modelName}_${partNum}_${formattedNow}"
    }
}