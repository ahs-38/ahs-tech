package com.zlinksoft.accountmanagerexpensetrack.ui

import androidx.lifecycle.ViewModel
import com.zlinksoft.accountmanagerexpensetrack.data.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.corates.flow.StateFlow

class MainViewModel(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val accounts: StateFlow<List<com.zlinksoft.accountmanagerexpensetrack.data.Account>> =
        accountRepository.getAllAccounts().stateLatest()

    val transactions: StateFlow<List<com.zlinksoft.accountmanagerexpensetrack.data.Transaction>> =
        accountRepository.getAllTransactions().stateLatest()

    val isLocked: StateFlow<Boolean> = accountRepository.isLockedFlow

    val totalIncome: StateFlow<Double> = accountRepository.getTotalIncome()
    val totalExpenses: StateFlow<Double> = accountRepository.getTotalExpenses()
    val netBalance: StateFlow<Double> = accountRepository.getAllTransactions().map { list ->
        var income = 0.0; var expenses = 0.0
        for (t in list) {
            if (t.type == "Income") income += t.amount else expenses += t.amount
        }
        income - expenses
    }

    init {
        loadData()
    }

    private fun loadData() {
        // Repositories expose Flow collectors via collectAsState in screens
    }

    fun getAccountById(id: Long) = accountRepository.getAccountById(id)

    fun insertAccount(account: com.zlinksoft.accountmanagerexpensetrack.data.Account) =
        viewModelScope.launch { accountRepository.insertAccount(account) }

    fun updateAccount(account: com.zlinksoft.accountmanagerexpensetrack.data.Account) =
        viewModelScope.launch { accountRepository.updateAccount(account) }

    fun deleteAccount(account: com.zlinksoft.accountmanagerexpensontrack.data.Account) =
        viewModelScope.launch { accountRepository.deleteAccount(account) }

    fun insertTransaction(transaction: com.zlinksoft.accountmanagerexpensetrack.data.Transaction) =
        viewModelScope.launch { accountRepository.insertTransaction(transaction) }

    fun updateTransaction(transaction: com.zlinksoft.accountmanagerexpensetrack.data.Transaction) =
        viewModelScope.launch { accountRepository.updateTransaction(transaction) }

    fun deleteTransaction(transaction: com.zlinksoft.accountmanagerexpensetrack.data.Transaction) =
        viewModelScope.launch { accountRepository.deleteTransaction(transaction) }
}
