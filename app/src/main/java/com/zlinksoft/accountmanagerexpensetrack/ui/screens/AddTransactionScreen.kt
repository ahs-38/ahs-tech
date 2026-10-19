package com.zlinksoft.accountmanagerexpensetrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsState
import androidx.navigation.NavController
import com.zlinksoft.accountmanagerexpensetrack.data.Transaction
import com.zlinksoft.accountmanagerexpensetrack.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController:NavController
) {
    val accounts by viewModel.accounts.collectAsState(initial = emptyList())

    var selectedAccount by remember { mutableStateOf(accounts.firstOrNull()?.id ?: 0) }
    var transactionType by remember { mutableStateOf("Expense") }
    var category by remember { mutableStateOf("General") }
    var amount by remember { mutableStateOf("") }
    var currency by remember { mutableStateOf("USD") }
    var note by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(System.currentTimeMillis()) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Transaction") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Account Selector
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Account", fontSize = 14.sp, color = Color.Gray)
                    val selectedAccountName = accounts.find { it.id == selectedAccount }?.name ?: "Select Account"
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(selectedAccountName, modifier = Modifier.fillMaxWidth())
                        IconButton(onClick = { }, modifier = Modifier.align(Alignment.End)) {
                            Icon(painter = painterResource(id = R.drawable.ic_dropdown), contentDescription = "Select")
                        }
                    }
                }
            }

            // Type Toggle
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Card(
                    modifier = Modifier.weight(1f),
                    onClick = { transactionType = "Expense" }
                ) {
                    Button(
                        onClick = { transactionType = "Expense" },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (transactionType == "Expense") MaterialTheme.colorScheme.primary else Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Expense")
                    }
                }
                Card(
                    modifier = Modifier.weight(1f),
                    onClick = { transactionType = "Income" }
                ) {
                    Button(
                        onClick = { transactionType = "Income" },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (transactionType == "Income") MaterialTheme.colorScheme.primary else Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Income")
                    }
                }
            }

            // Category
            TextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )

            // Amount
            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Currency
            TextField(
                value = currency,
                onValueChange = { currency = it },
                label = { Text("Currency") },
                modifier = Modifier.fillMaxWidth()
            )

            // Note
            TextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Note") },
                modifier = Modifier.fillMaxWidth()
            )

            // Date
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Date: ", fontSize = 14.sp)
                    Text(text = "${date}", modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* Date picker */ }, modifier = Modifier.size(32.dp)) {
                        Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = "Date")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    val amountDouble = amount.toDoubleOrNull() ?: 0.0
                    val transaction = Transaction(
                        id = 0,
                        accountId = selectedAccount,
                        type = transactionType,
                        category = category,
                        amount = amountDouble,
                        currency = currency,
                        note = note,
                        date = date.toString().toLongOrNull() ?: System.currentTimeMillis(),
                        timestamp = System.currentTimeMillis()
                    )
                    viewModel.insertTransaction(transaction)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Save Transaction")
            }
        }
    }
}