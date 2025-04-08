package com.example.spendwise.ui.transactions.add_transaction.model

sealed interface AddTransactionEffect {
    data object  NavigateBack : AddTransactionEffect
}