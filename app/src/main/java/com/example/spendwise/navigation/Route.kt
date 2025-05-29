package com.example.spendwise.navigation

import kotlinx.serialization.Serializable

sealed interface BottomNavRoute {
    @Serializable
    data object HomeRoute : BottomNavRoute

    @Serializable
    data object TransactionRoute : BottomNavRoute

    @Serializable
    data object BudgetRoute : BottomNavRoute

    @Serializable
    data object OtherRoute : BottomNavRoute
}


sealed interface AppRoute {

    @Serializable
    data object AddTransactionRoute : AppRoute

    @Serializable
    data class EditTransactionRoute(val transactionItemId: Int) : AppRoute
}
