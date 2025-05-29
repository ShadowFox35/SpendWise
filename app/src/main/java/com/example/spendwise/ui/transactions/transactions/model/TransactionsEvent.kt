package com.example.spendwise.ui.transactions.transactions.model

import com.example.spendwise.ui.common.model.BaseEvent

sealed interface TransactionsEvent: BaseEvent {
    data object Initial : TransactionsEvent
    data object OnFloatActionButtonClick : TransactionsEvent
    data class OnEditActionButtonClick(val itemId: Int) : TransactionsEvent
}