package com.example.spendwise.ui.transactions.transactions.model

import com.example.spendwise.navigation.EditTransactionRoute
import com.example.spendwise.navigation.AddTransactionRoute

sealed interface TransactionsEffect {
    data class OpenAddTransactionScreen(val route: EditTransactionRoute) :TransactionsEffect
    data class OpenAddTransactionRouteWithOutArgs(val route: AddTransactionRoute) :TransactionsEffect
}