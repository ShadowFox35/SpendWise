package com.example.spendwise.data.providers.database.transactions

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendwise.ui.transactions.transactions.components.TransactionItemUiModel
import java.time.LocalDate

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Double,
    val date: LocalDate,
)

fun TransactionEntity.toUiModel(): TransactionItemUiModel {
    return TransactionItemUiModel(
        id = id,
        title = title,
        date = date.toString(),
        amount = amount.toString(),
    )
}