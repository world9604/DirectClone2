package com.example.directclone2.model.usecase

import net.lingala.zip4j.ZipFile
import java.io.File
import java.util.zip.ZipException

object DecompressZipFileUseCase {

    operator fun invoke(file: File, destinationPath: String, password: String): File {
        return try {
            val zipFile = ZipFile(file)
            if (zipFile.isEncrypted) zipFile.setPassword(password.toCharArray())
            zipFile.extractAll(destinationPath)
            File(destinationPath, file.name)
        } catch (e: ZipException) {
            e.printStackTrace()
            file
        }
    }
}