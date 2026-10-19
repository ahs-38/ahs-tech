package com.zlinksoft.accountmanagerexpensetrack.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("BootReceiver", "Boot completed")
        // Re-schedule any alarms or work
    }
}