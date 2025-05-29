package com.example.spendwise.ui.transactions.add_transaction.model

import com.example.spendwise.ui.common.model.BaseEvent

sealed interface AddTransactionEvent: BaseEvent {
    data object Initial : AddTransactionEvent

    data object OnDateEditButtonClick : AddTransactionEvent

    data class OnDateFieldChanged(
        val newDate: Long?,
    ) : AddTransactionEvent

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