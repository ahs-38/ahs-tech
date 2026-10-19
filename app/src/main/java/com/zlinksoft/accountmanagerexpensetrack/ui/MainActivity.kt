package com.zlinksoft.accountmanagerexpensetrack.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.WorkManager
import com.zlinksoft.accountmanagerexpensetrack.ui.theme.AccountManagerExpenseTrackTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup splash screen
        installSplashScreen()

        setContent {
            AccountManagerExpenseTrackTheme {
                // Main navigation setup
                AccountManagerNavHost()
            }
        }
    }
}