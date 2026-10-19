package com.zlinksoft.accountmanagerexpensetrack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zlinksoft.accountmanagerexpensetrack.data.Account
import com.zlinksoft.accountmanagerexpensetrack.data.AccountRepository
import com.zlinksoft.accountmanagerexpensetrack.data.Transaction
import com.zlinksoft.accountmanagerexpensetrack.utils.BiometricHelper
import com.zlinksoft.accountmanagerexpensetrack.utils.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val accountRepository: AccountRepository,
    private val prefsManager: PreferencesManager
) : ViewModel() {

    // Accounts
    private val _accounts = MutableStateFlow<List<Account>>(emptyList())
    val accounts: StateFlow<List<Account>> = _accounts.asStateFlow()

    // Transactions
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    // Lock state
    private val _isLocked = MutableStateFlow(prefsManager.isLocked)
    val isLocked: StateFlow<Boolean> = _isLocked.asStateFlow()

    private val _pinCode = MutableStateFlow(prefsManager.pinCode)
    val pinCode: StateFlow<String> = _pinCode.asStateFlow()

    // Stats
    val totalIncome: StateFlow<Double> = accountRepository.getTotalByType("Income")
    val totalExpenses: StateFlow<Double> = accountRepository.getTotalByType("Expense")
    val netBalance: StateFlow<Double> = totalIncome.map { income ->
        totalExpenses.value - income
    }

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            accountRepository.getAllAccounts().collect { accounts ->
                _accounts.value = accounts
            }
            accountRepository.getAllTransactions().collect { transactions ->
                _transactions.value = transactions
            }
        }
    }

    fun getAccountById(id: Long) = accountRepository.getAccountById(id)

    fun insertAccount(account: Account) = viewModelScope.launch {
        accountRepository.insertAccount(account)
    }

    fun updateAccount(account: Account) = viewModelScope.launch {
        accountRepository.updateAccount(account)
    }

    fun deleteAccount(account: Account) = viewModelScope.launch {
        accountRepository.deleteAccount(account)
    }

    fun insertTransaction(transaction: Transaction) = viewModelScope.launch {
        accountRepository.insertTransaction(transaction)
    }

    fun updateTransaction(transaction: Transaction) = viewModelScope.launch {
        accountRepository.updateTransaction(transaction)
    }

    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch {
        accountRepository.deleteTransaction(transaction)
    }

    fun unlockApp() {
        _isLocked.value = false
    }

    fun unlockWithBiometric() {
        // Will be handled by BiometricHelper
        _isLocked.value = false
    }

    fun setLocked(locked: Boolean) {
        _isLocked.value = locked
        prefsManager.isLocked = locked
    }

    fun setPinCode(pin: String) {
        _pinCode.value = pin
        prefsManager.pinCode = pin
    }
}