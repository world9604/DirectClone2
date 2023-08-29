package com.example.directclone2.model.usecase

import android.util.Log
import com.example.directclone2.model.ProfileRepository
import com.example.directclone2.model.data.Profile
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


object CreateFileUseCase {

    private const val TAG = "CreateFileUseCase"

    operator fun invoke(profile: Profile): Boolean {
        profile.backupApps.forEach {
            try {
                val source = File(it.data)
                val target = File("${profile.filePath}${profile.fileName}", source.name)
                copyDirectory(source, target)
            } catch (e: Exception) {
                Log.e(TAG, "Exception Message : ${e.message}", e)
            }
        }
        return true
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
}