package com.example.spendwise.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: BottomNavRoute,
    val icon: ImageVector,
    val label: String
) {
    data object Home : BottomNavItem(
        route = HomeRoute,
        icon = Icons.Default.Home,
        label = "Home"
    )

    data object Transactions : BottomNavItem(
        route = TransactionRoute,
        icon = Icons.Default.Check,
        label = "Transactions"
    )

    data object Budget : BottomNavItem(
        route = BudgetRoute,
        icon = Icons.Default.AddCircle,
        label = "Budget"
    )

    data object Other : BottomNavItem(
        route = OtherRoute,
        icon = Icons.Default.MoreVert,
        label = "Other"
    )
}

