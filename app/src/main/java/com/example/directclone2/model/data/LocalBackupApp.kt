package com.example.directclone2.model.data

import com.example.directclone2.ui.screen.main.AppItem

data class LocalBackupApp (
    val app: AppForBackup = AppForBackup.Settings,
) {
    val packageName: String = app.packageName
    val activityName: String = app.activityName
    val data: String = app.data
    val appName: String = app.appName
    val isPreInstalledApp: Boolean = app.isPreInstalledApp

    enum class AppForBackup(val packageName: String,
                            val activityName: String,
                            val data: String,
                            val appName: String,
                            val isPreInstalledApp: Boolean) {
        ProgramButtons(
            packageName = "device.apps.button",
            activityName = "device.apps.button.ProgramButtonsActivity",
            data = "/data/system/HiJackDBHelper.db",
            appName = "Program Buttons",
            isPreInstalledApp = true),
        Quickstep(
            packageName = "",
            activityName = "",
            data = "",
            appName = "Quickstep",
            isPreInstalledApp = true),
        ScanSettings(
            packageName = "device.settings.scanner",
            activityName = "device.settings.scanner.ScanSettingsActivity",
            data = "/data/system/scanner/n5603.reg",
            appName = "ScanSettings",
            isPreInstalledApp = true),
        EmKiosk(
            packageName = "device.apps.emkioskconfig",
            activityName = "device.apps.emkioskconfig.SplashActivity",
            data = "",
            appName = "EmKiosk",
            isPreInstalledApp = true),
        Settings(
            packageName = "",
            activityName = "",
            data = "/storage/emulated/0/",
            appName = "Settings",
            isPreInstalledApp = true)
    }
}

fun AppItem.toLocal() = LocalBackupApp(
    app = when(appName) {
        LocalBackupApp.AppForBackup.ProgramButtons.appName -> LocalBackupApp.AppForBackup.ProgramButtons
        LocalBackupApp.AppForBackup.Quickstep.appName -> LocalBackupApp.AppForBackup.Quickstep
        LocalBackupApp.AppForBackup.ScanSettings.appName -> LocalBackupApp.AppForBackup.ScanSettings
        LocalBackupApp.AppForBackup.Settings.appName -> LocalBackupApp.AppForBackup.Settings
        LocalBackupApp.AppForBackup.EmKiosk.appName -> LocalBackupApp.AppForBackup.EmKiosk
        else -> LocalBackupApp.AppForBackup.Settings
    }
)

@JvmName("externalToLocal")
fun List<AppItem>.toLocal() = map(AppItem::toLocal)

@JvmName("localToExternal")
fun List<LocalBackupApp>.toExternal() = map(LocalBackupApp::toExternal)

fun LocalBackupApp.toExternal() = AppItem(
    packageName = packageName,
    activityName = activityName,
    appName = appName,
    isPreInstalledApp = isPreInstalledApp,
)

fun LocalBackupApp.AppForBackup.toLocal() = LocalBackupApp(app = this)

@JvmName("appForBackupToExternal")
fun List<LocalBackupApp.AppForBackup>.toExternal() = map { (it::toLocal as LocalBackupApp)::toExternal }
