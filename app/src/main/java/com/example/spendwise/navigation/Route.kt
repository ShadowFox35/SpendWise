package com.example.spendwise.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

object BottomNavigationRoutes {
    const val HOME_ROUTE = "home"
    const val TRANSACTION_ROUTE = "transactions"
    const val BUDGET_ROUTE = "budget"
    const val OTHER_ROUTE = "other"
}

object AppRoutes {
    const val ADD_TRANSACTION_ROUTE = "add_transaction"
}

@JvmInline
value class Route(val route: String)

val NavController.currentRoute: Route
    @Composable
    get() = Route(currentBackStackEntryAsState().value?.destination?.route ?: "Empty route")

fun Route?.toRouteName(): String {
    return this?.route?.split('_')
        ?.joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } } ?: "Empty route name"
}

fun Route?.isBottomNavRoute(): Boolean {
    return this?.route in setOf(
        BottomNavigationRoutes.HOME_ROUTE,
        BottomNavigationRoutes.TRANSACTION_ROUTE,
        BottomNavigationRoutes.BUDGET_ROUTE,
        BottomNavigationRoutes.OTHER_ROUTE
    )
}