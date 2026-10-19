package com.zlinksoft.accountmanagerexpensetrack.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: String = "Cash",
    val balance: Double = 0.0,
    val currency: String = "USD",
    val color: Long = 0xFF2196F3,
    val icon: String = "Wallet"
)