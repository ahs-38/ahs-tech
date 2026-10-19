package com.zlinksoft.accountmanagerexpensetrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlinksoft.accountmanagerexpensetrack.ui.MainViewModel
import com.zlinksoft.accountmanagerexpensetrack.utils.PreferencesManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LockScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val isLocked by remember { mutableStateOf(PreferencesManager.isLocked(context)) }
    val pinCode by remember { mutableStateOf(PreferencesManager.getPinCode(context)) }

    if (isLocked) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Account Manager",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text("Enter PIN", fontSize = 24.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            // PIN Input
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(6) { index ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color(0xFF333333), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            if (index < pinCode.length) "*" else "",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Touch to unlock button
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3)),
                onClick = { },
                modifier = Modifier.clickable { }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Touch to Unlock",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Biometric login option
            TextButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Use Biometric", color = Color(0xFF2196F3))
            }
        }
    } else {
        // App is unlocked, show normal content
        DashboardScreen(viewModel = viewModel, navController = rememberNavController())
    }
}