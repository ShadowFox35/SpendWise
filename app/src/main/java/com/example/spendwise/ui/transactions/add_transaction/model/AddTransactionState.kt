package com.example.spendwise.ui.transactions.add_transaction.model

data class AddTransactionState(
    val saveTransactionEnabled: Boolean = false,
    val transactionTitle: String = "",
    val transactionAmount: String = "",
)