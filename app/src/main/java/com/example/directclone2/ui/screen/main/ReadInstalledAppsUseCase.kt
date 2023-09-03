package com.example.directclone2.ui.screen.main

import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import java.io.File


object ReadInstalledAppsUseCase {

    private val TAG = "ReadInstalledAppsUseCase"
    private val installAppsBackupDir = "${Environment.getExternalStorageDirectory()}/DirectClone/Apps/"

    operator suspend fun invoke(context: Context): List<AppItem> {
        val dir = File(installAppsBackupDir)
        dir.mkdirs()
        val filesInDir = ArrayList<File>()
        findAllFilesWithExtension(dir, ".apk", filesInDir)
        return filesInDir
            .filter { context.packageManager.getPackageArchiveInfo(
                it.absolutePath, PackageManager.GET_ACTIVITIES) != null }
            .map {
                val packageInfo = context.packageManager.getPackageArchiveInfo(
                    it.absolutePath, PackageManager.GET_ACTIVITIES)!!
                AppItem(
                    appName = it.name,
                    packageName = packageInfo.packageName,
                    sourceDir = it.absolutePath,
                    isPreInstalledApp = false)
            }
    }

    private fun findAllFilesWithExtension(dir: File, extension: String, listFiles: MutableList<File>) {
        val childList = dir.listFiles().toList()
        childList.forEach {
            if (it.isDirectory) findAllFilesWithExtension(it, extension, listFiles)
            else if(it.name.endsWith(extension)) listFiles.add(it)
        }
    }
}