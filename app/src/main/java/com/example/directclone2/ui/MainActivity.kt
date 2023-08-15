package com.example.directclone2.ui

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.directclone2.ui.theme.DirectCloneTheme
import com.gun0912.tedpermission.coroutine.TedPermission
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val permissionResult = TedPermission.create().setPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE).check()

            if (permissionResult.isGranted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if (!Environment.isExternalStorageManager()) requestManageAllFilesAccessPermission() else viewUI()
                }
            } else {
                Toast.makeText(applicationContext, "Is Not Granted!!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun requestManageAllFilesAccessPermission() {
        val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
        resultLauncher.launch(intent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        viewUI()
        /*
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(applicationContext, "Activity.RESULT_OK!!", Toast.LENGTH_LONG).show()
            // There are no request codes
            val data: Intent? = result.data
        }
         */
    }

    private fun viewUI() {
        setContent {
            DirectCloneTheme {
                DirectCloneApp()
            }
        }
    }
}
