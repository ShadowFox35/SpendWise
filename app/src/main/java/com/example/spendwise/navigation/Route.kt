package com.example.spendwise.navigation

import kotlinx.serialization.Serializable

interface BottomNavRoute

@Serializable
data object HomeRoute : BottomNavRoute

@Serializable
data object TransactionRoute : BottomNavRoute

@Serializable
data object BudgetRoute : BottomNavRoute

@Serializable
data object OtherRoute : BottomNavRoute

interface AppRoute {
    val title: String
}

@Serializable
data object AddTransactionRoute : AppRoute {
    override val title = "Add Transaction"
}

@Serializable
data class EditTransactionRoute(val transactionItemId: Int) : AppRoute {
    override val title = "Edit Transaction"
}