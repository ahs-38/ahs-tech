package com.zlinksoft.accountmanagerexpensetrack.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup splash screen
        installSplashScreen()

        setContent {
            AccountManagerExpenseTrackTheme {
                // Simple hello world for now
                androidx.compose.material3.Text("Account Manager Expense Track App")
            }
        }
    }
}