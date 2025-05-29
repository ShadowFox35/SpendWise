package com.example.spendwise.ui.transactions.add_transaction.model

import com.example.spendwise.ui.common.model.BaseEffect

sealed interface AddTransactionEffect: BaseEffect {
    data object NavigateBack : AddTransactionEffect
}