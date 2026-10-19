package com.zlinksoft.accountmanagerexpensetrack.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AccountRepository(
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao
) {

    private val _isLocked = MutableStateFlow(false)
    val isLockedFlow: StateFlow<Boolean> = _isLocked.asStateFlow()

    private val _pinCode = MutableStateFlow("")
    val pinCodeFlow: StateFlow<String> = _pinCode.asStateFlow()

    fun getAllAccounts(): Flow<List<Account>> = accountDao.getAllAccounts()

    suspend fun getAccountById(id: Long): Account? = accountDao.getAccountById(id)

    suspend fun insertAccount(account: Account): Long = accountDao.insert(account)

    suspend fun updateAccount(account: Account) = accountDao.update(account)

    suspend fun deleteAccount(account: Account) = accountDao.delete(account)

    fun getAllTransactions(): Flow<List<Transaction>> = transactionDao.getAllTransactions()

    fun getTransactionsByAccount(accountId: Long): Flow<List<Transaction>> =
        transactionDao.getTransactionsByAccount(accountId)

    fun getTransactionsByAccountAndType(accountId: Long, type: String): Flow<List<Transaction>> =
        transactionDao.getTransactionsByAccountAndType(accountId, type)

    fun getTransactionsByType(type: String): Flow<List<Transaction>> =
        transactionDao.getTransactionsByType(type)

    suspend fun insertTransaction(transaction: Transaction): Long = transactionDao.insert(transaction)

    suspend fun updateTransaction(transaction: Transaction) = transactionDao.update(transaction)

    suspend fun deleteTransaction(transaction: Transaction) = transactionDao.delete(transaction)

    suspend fun deleteTransactionById(id: Long) = transactionDao.deleteById(id)

    fun getTotalByType(type: String): Flow<Double> = transactionDao.getTotalByType(type)

    fun getTotalByAccount(accountId: Long): Flow<Double> = transactionDao.getTotalByAccount(accountId)

    fun getTotalIncome(): Flow<Double> = transactionDao.getTotalByType("Income")

    fun getTotalExpenses(): Flow<Double> = transactionDao.getTotalByType("Expense")

    fun isLocked(): Boolean = _isLocked.value

    fun setLocked(locked: Boolean) {
        _isLocked.value = locked
    }

    fun getPinCode(): String = _pinCode.value

    fun setPinCode(pin: String) {
        _pinCode.value = pin
    }
}