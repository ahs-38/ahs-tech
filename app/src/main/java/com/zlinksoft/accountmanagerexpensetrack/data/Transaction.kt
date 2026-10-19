package com.zlinksoft.accountmanagerexpensetrack.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("accountId")]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val accountId: Long,
    val type: String = "Expense",
    val category: String = "General",
    val amount: Double = 0.0,
    val currency: String = "USD",
    val note: String = "",
    val date: Long = System.currentTimeMillis(),
    val timestamp: Long = System.currentTimeMillis()
)