package com.example.spendwise.data.providers.database.transactions

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendwise.ui.transactions.transactions.components.TransactionItemUiModel

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val date: String,
)

fun TransactionEntity.toUiModel(): TransactionItemUiModel {
    return TransactionItemUiModel(
        id = id,
        title = title,
        date = date,
        amount = amount.toString(),
    )
}