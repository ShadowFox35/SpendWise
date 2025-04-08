package com.example.spendwise.ui.transactions.add_transaction.model

sealed interface AddTransactionEvent {
    data class OnTitleFieldChanged(
        val newTitle: String,
    ) : AddTransactionEvent

    data class OnAmountFieldChanged(
        val newAmount: String,
    ) : AddTransactionEvent

    data class OnSubmitButtonClick(
        val transactionId: Int?,
    ) : AddTransactionEvent

    data class OnDeleteButtonClick(
        val transactionId: Int,
    ) : AddTransactionEvent
}