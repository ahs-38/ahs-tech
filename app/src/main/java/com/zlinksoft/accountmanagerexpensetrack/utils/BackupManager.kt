package com.zlinksoft.accountmanagerexpensetrack.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object BackupManager {
    fun createBackup(context: Context): Boolean {
        try {
            val dbFile = context.getDatabasePath("account_manager_db")
            val backupDir = context.getExternalFilesDir(null)
            val backupFile = File(backupDir, "account_manager_backup.db")
            FileInputStream(dbFile).use { input ->
                FileOutputStream(backupFile).use { output ->
                    input.copyTo(output)
                }
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun restoreBackup(context: Context): Boolean {
        try {
            val backupDir = context.getExternalFilesDir(null)
            val backupFile = File(backupDir, "account_manager_backup.db")
            if (!backupFile.exists()) return false
            FileInputStream(backupFile).use { input ->
                FileOutputStream(context.getDatabasePath("account_manager_db")).use { output ->
                    input.copyTo(output)
                }
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
}