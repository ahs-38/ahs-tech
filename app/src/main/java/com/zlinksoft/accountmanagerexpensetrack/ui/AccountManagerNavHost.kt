package com.zlinksoft.accountmanagerexpensetrack.ui

import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.rememberNavController

@Composable
fun AccountManagerNavHost() {
    NavHost(
        navController = rememberNavController(),
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(
                viewModel = hiltViewModel(),
                navController = rememberNavController()
            )
        }
        composable("accounts") {
            AccountsScreen(
                viewModel = hiltViewModel(),
                navController = rememberNavController()
            )
        }
        composable("add_transaction") {
            AddTransactionScreen(
                viewModel = hiltViewModel(),
                navController = rememberNavController()
            )
        }
        composable("reports") {
            ReportsScreen(
                viewModel = hiltViewModel()
            )
        }
        composable("settings") {
            SettingsScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}