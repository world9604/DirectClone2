package com.example.directclone2.model.usecase

import android.util.Log
import com.example.directclone2.model.data.LocalProfile
import net.lingala.zip4j.ZipFile
import net.lingala.zip4j.model.ZipParameters
import net.lingala.zip4j.model.enums.CompressionLevel
import net.lingala.zip4j.model.enums.CompressionMethod
import net.lingala.zip4j.model.enums.EncryptionMethod
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


object CreateFileUseCase {

    private const val TAG = "CreateFileUseCase"

    operator fun invoke(localProfile: LocalProfile): Boolean {
        try {
            val targetDir = File("${localProfile.filePath}${localProfile.fileName}")
            localProfile.backupApps.forEach {
                val source = File(it.sourceDir)
                val target = File(targetDir, source.name)
                copyDirectory(source, target)
            }
            if (targetDir.exists()) {
                compressZipFile(targetDir, localProfile.password)
                return true
            }
            return false
        } catch (e: Exception) {
            Log.e(TAG, "Exception Message : ${e.message}", e)
            return false
        }
    }

    @Throws(IOException::class)
    private fun copyDirectory(sourceLocation: File, targetLocation: File) {
        if (sourceLocation.isDirectory) {
            if (!targetLocation.exists() && !targetLocation.mkdirs()) {
                throw IOException("Cannot create dir " + targetLocation.absolutePath)
            }
            val children = sourceLocation.list()
            for (i in children.indices) {
                copyDirectory(
                    File(sourceLocation, children[i]),
                    File(targetLocation, children[i])
                )
            }
        } else {
            val directory = targetLocation.parentFile
            if (directory != null && !directory.exists() && !directory.mkdirs()) {
                throw IOException("Cannot create dir " + directory.absolutePath)
            }
            val input: InputStream = FileInputStream(sourceLocation)
            val output: OutputStream = FileOutputStream(targetLocation)

            val buf = ByteArray(1024)
            var len: Int
            while (input.read(buf).also { len = it } > 0) {
                output.write(buf, 0, len)
            }
            input.close()
            output.close()
        }
    }

    private fun compressZipFile(file: File, password: String): File {
        val zipFile = ZipFile("${file.absolutePath}.zip")
        val zipParameters = ZipParameters()
        zipParameters.compressionLevel = CompressionLevel.NORMAL
        zipParameters.compressionMethod = CompressionMethod.DEFLATE

        if (password.isNotBlank()) {
            zipFile.setPassword(password.toCharArray())
            zipParameters.isEncryptFiles = true
            zipParameters.encryptionMethod = EncryptionMethod.ZIP_STANDARD
        }

        zipFile.addFolder(file, zipParameters)
        file.deleteRecursively()
        return zipFile.file
        /*
        mediaScanFile(mBackupFile)
        mediaScanFile(zipFile.file)
        */
    }
}