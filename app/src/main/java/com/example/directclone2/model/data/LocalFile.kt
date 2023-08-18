package com.example.directclone2.model.data

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

data class LocalFile(
    val file: File = File(""),
    val createdDate: Date = Date(),
    val password: String = "",
) {
    val name: String by lazy { file.name }
    val savedLocation: String by lazy { file.absolutePath }
    val formattedCreatedDate: String by lazy { SimpleDateFormat("yyyy/MM/dd HH:mm").format(createdDate) }
}