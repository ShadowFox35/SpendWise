package com.example.spendwise.ui.transactions.transactions.model

sealed interface TransactionsEvent {
    data object OnFloatActionButtonClick:TransactionsEvent
}