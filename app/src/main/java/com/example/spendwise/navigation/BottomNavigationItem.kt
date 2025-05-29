package com.example.spendwise.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.spendwise.R

sealed class BottomNavItem(
    val route: BottomNavRoute,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    data object Home : BottomNavItem(
        route = BottomNavRoute.HomeRoute,
        icon = Icons.Default.Home,
        label = R.string.navigation_bar_home
    )

    data object Transactions : BottomNavItem(
        route = BottomNavRoute.TransactionRoute,
        icon = Icons.Default.Check,
        label = R.string.navigation_bar_transactions
    )

    data object Budget : BottomNavItem(
        route = BottomNavRoute.BudgetRoute,
        icon = Icons.Default.AddCircle,
        label = R.string.navigation_bar_budget
    )

    data object Other : BottomNavItem(
        route = BottomNavRoute.OtherRoute,
        icon = Icons.Default.MoreVert,
        label = R.string.navigation_bar_other
    )
}

