package com.example.spendwise.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    data object Home : BottomNavItem(
        route = BottomNavigationRoutes.HOME_ROUTE,
        icon = Icons.Default.Home,
        label = "Home"
    )

    data object Transactions : BottomNavItem(
        route = BottomNavigationRoutes.TRANSACTION_ROUTE,
        icon = Icons.Default.Check,
        label = "Transactions"
    )

    data object Budget : BottomNavItem(
        route = BottomNavigationRoutes.BUDGET_ROUTE,
        icon = Icons.Default.AddCircle,
        label = "Budget"
    )

    data object Other : BottomNavItem(
        route = BottomNavigationRoutes.OTHER_ROUTE,
        icon = Icons.Default.MoreVert,
        label = "Other"
    )
}

