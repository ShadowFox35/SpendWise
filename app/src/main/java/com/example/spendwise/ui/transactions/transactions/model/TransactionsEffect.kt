package com.example.spendwise.ui.transactions.transactions.model

import com.example.spendwise.navigation.Route

sealed interface TransactionsEffect {
    data class OpenAddTransactionScreen(val route: Route) : TransactionsEffect
}