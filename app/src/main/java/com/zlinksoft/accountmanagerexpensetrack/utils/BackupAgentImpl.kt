package com.zlinksoft.accountmanagerexpensetrack.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class BackupAgentImpl : android.app.backup.BackupAgent() {

    override fun onBackup(
        oldState: android.os.ParcelFileDescriptor?,
        data: android.app.backup.BackupDataOutput?,
        newState: android.os.ParcelFileDescriptor?
    ) {
        try {
            val dbFile = context.getDatabasePath("account_manager_db")
            val backupDir = context.getExternalFilesDir(null)
            val backupFile = File(backupDir, "account_manager_backup.db")
            FileInputStream(dbFile).use { input ->
                FileOutputStream(backupFile).use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onRestore(
        data: android.app.backup.BackupDataInput?,
        appVersionCode: Int,
        newState: android.os.ParcelFileDescriptor?
    ) {
        try {
            val backupDir = context.getExternalFilesDir(null)
            val backupFile = File(backupDir, "account_manager_backup.db")
            if (!backupFile.exists()) return
            FileInputStream(backupFile).use { input ->
                FileOutputStream(context.getDatabasePath("account_manager_db")).use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}