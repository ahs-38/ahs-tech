package com.zlinksoft.accountmanagerexpensetrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zlinksoft.accountmanagerexpensetrack.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val income by viewModel.totalIncome.collectAsState(0.0)
    val expenses by viewModel.totalExpenses.collectAsState(0.0)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Reports") },
                navigationIcon = {
                    IconButton(onClick = { /* Back */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Summary Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Total Income", fontSize = 14.sp, color = Color.Gray)
                        Text("$${String.format("%.2f", income)}", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Green)
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Total Expenses", fontSize = 14.sp, color = Color.Gray)
                        Text("$${String.format("%.2f", expenses)}", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Red)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Income vs Expenses",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Simple Bar Chart
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                // Income bar
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val height = (income / (income + expenses + 1) * 200).coerceAtLeast(20).toInt()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height.dp)
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Green)
                        )
                        Text("Income", fontSize = 12.sp, color = Color.White)
                    }
                    Text("$${String.format("%.2f", income)}", fontSize = 12.sp)
                }
                // Expense bar
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val height = (expenses / (income + expenses + 1) * 200).coerceAtLeast(20).toInt()
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height.dp)
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Red)
                        )
                        Text("Expenses", fontSize = 12.sp, color = Color.White)
                    }
                    Text("$${String.format("%.2f", expenses)}", fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Category Breakdown",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Placeholder for category list
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Food & Dining: $120.00", fontSize = 16.sp)
                    Text("Transport: $80.00", fontSize = 16.sp)
                    Text("Shopping: $200.00", fontSize = 16.sp)
                    Text("Entertainment: $50.00", fontSize = 16.sp)
                }
            }
        }
    }
}
