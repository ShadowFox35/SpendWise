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
    data object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    data object Transactions : BottomNavItem("transactions", Icons.Default.Check, "Transactions")
    data object Budget : BottomNavItem("budget", Icons.Default.AddCircle, "Budget")
    data object Other : BottomNavItem("other", Icons.Default.MoreVert, "Other")
}
