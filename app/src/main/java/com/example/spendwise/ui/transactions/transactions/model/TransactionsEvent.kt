package com.example.spendwise.ui.transactions.transactions.model

sealed interface TransactionsEvent {
    data object Initial : TransactionsEvent
    data object OnFloatActionButtonClick : TransactionsEvent
    data class OnEditActionButtonClick(
        val itemId: Int,
    ) : TransactionsEvent
}