package com.zlinksoft.accountmanagerexpensetrack.ui

import androidx.lifecycle.ViewModel
import com.zlinksoft.accountmanagerexpensetrack.data.Account
import com.zlinksoft.accountmanagerexpensetrack.data.AccountRepository
import com.zlinksoft.accountmanagerexpensetrack.data.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val accounts: StateFlow<List<Account>> = accountRepository.getAllAccounts().asStateFlow(emptyList())
    val transactions: StateFlow<List<Transaction>> = accountRepository.getAllTransactions().asStateFlow(emptyList())
    val isLocked: StateFlow<Boolean> = accountRepository.isLockedFlow
    val totalIncome: StateFlow<Double> = accountRepository.getTotalIncome()
    val totalExpenses: StateFlow<Double> = accountRepository.getTotalExpenses()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            accountRepository.getAllAccounts().collect { _accounts.value = it }
        }
        viewModelScope.launch {
            accountRepository.getAllTransactions().collect { _transactions.value = it }
        }
    }

    fun getAccountById(id: Long) = accountRepository.getAccountById(id)

    fun insertAccount(account: Account) = viewModelScope.launch { accountRepository.insertAccount(account) }

    fun updateAccount(account: Account) = viewModelScope.launch { accountRepository.updateAccount(account) }

    fun deleteAccount(account: Account) = viewModelScope.launch { accountRepository.deleteAccount(account) }

    fun insertTransaction(transaction: Transaction) = viewModelScope.launch { accountRepository.insertTransaction(transaction) }

    fun updateTransaction(transaction: Transaction) = viewModelScope.launch { accountRepository.updateTransaction(transaction) }

    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch { accountRepository.deleteTransaction(transaction) }
}