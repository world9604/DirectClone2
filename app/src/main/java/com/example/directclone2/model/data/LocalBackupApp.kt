package com.example.directclone2.model.data

import com.example.directclone2.ui.screen.main.AppItem

data class LocalBackupApp (
    val packageName: String,
    val activityName: String,
    val sourceDir: String,
    val appName: String,
    val isPreInstalledApp: Boolean

    /*
    val app: AppForBackup = AppForBackup.Settings,
    val packageName: String = app.packageName,
    val activityName: String = app.activityName,
    val sourceDir: String = app.sourceDir,
    val appName: String = app.appName,
    val isPreInstalledApp: Boolean = app.isPreInstalledApp
     */
) {
    /*
    val packageName: String = app.packageName
    val activityName: String = app.activityName
    val sourceDir: String = app.sourceDir
    val appName: String = app.appName
    val isPreInstalledApp: Boolean = app.isPreInstalledApp
    */

    enum class AppForBackup(val packageName: String,
                            val activityName: String,
                            val sourceDir: String,
                            val appName: String,
                            val isPreInstalledApp: Boolean) {
        ProgramButtons(
            packageName = "device.apps.button",
            activityName = "device.apps.button.ProgramButtonsActivity",
            sourceDir = "/data/system/HiJackDBHelper.db",
            appName = "Program Buttons",
            isPreInstalledApp = true),
        Quickstep(
            packageName = "",
            activityName = "",
            sourceDir = "",
            appName = "Quickstep",
            isPreInstalledApp = true),
        ScanSettings(
            packageName = "device.settings.scanner",
            activityName = "device.settings.scanner.ScanSettingsActivity",
            sourceDir = "/data/system/scanner",
            appName = "ScanSettings",
            isPreInstalledApp = true),
        EmKiosk(
            packageName = "device.apps.emkioskconfig",
            activityName = "device.apps.emkioskconfig.SplashActivity",
            sourceDir = "/storage/emulated/0/EmKiosk.json",
            appName = "EmKiosk",
            isPreInstalledApp = true),
        Settings(
            packageName = "",
            activityName = "",
            sourceDir = "/data/data/com.example.directclone2/databases",
            appName = "Settings",
            isPreInstalledApp = true)
    }
}

fun AppItem.toLocal() = LocalBackupApp(
    packageName = packageName,
    activityName = activityName,
    appName = appName,
    sourceDir = sourceDir,
    isPreInstalledApp = isPreInstalledApp
)

@JvmName("externalToLocal")
fun List<AppItem>.toLocal() = filter{it.selected}.map(AppItem::toLocal)

@JvmName("localToExternal")
fun List<LocalBackupApp>.toExternal() = map(LocalBackupApp::toExternal)

fun LocalBackupApp.toExternal() = AppItem(
    packageName = packageName,
    activityName = activityName,
    sourceDir = sourceDir,
    appName = appName,
    isPreInstalledApp = isPreInstalledApp,
)


fun LocalBackupApp.AppForBackup.toLocal() = LocalBackupApp(
    packageName = packageName,
    activityName = activityName,
    sourceDir = sourceDir,
    appName = appName,
    isPreInstalledApp = isPreInstalledApp
)

/*
@JvmName("appForBackupToExternal")
fun List<LocalBackupApp.AppForBackup>.toExternal() = map { (it::toLocal as LocalBackupApp)::toExternal }
 */