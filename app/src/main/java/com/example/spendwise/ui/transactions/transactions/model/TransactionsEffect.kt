package com.example.spendwise.ui.transactions.transactions.model

import com.example.spendwise.navigation.AppRoute
import com.example.spendwise.ui.common.model.BaseEffect

sealed interface TransactionsEffect : BaseEffect {
    data class OpenEditTransactionScreen(val route: AppRoute.EditTransactionRoute) :
        TransactionsEffect

    data class OpenAddTransactionScreen(val route: AppRoute.AddTransactionRoute) :
        TransactionsEffect
}