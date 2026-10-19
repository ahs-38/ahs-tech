package com.zlinksoft.accountmanagerexpensetrack.ui

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.zlinksoft.accountmanagerexpensetrack.utils.BackupManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BackupWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            // Perform periodic backup
            BackupManager.createBackup(applicationContext)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}