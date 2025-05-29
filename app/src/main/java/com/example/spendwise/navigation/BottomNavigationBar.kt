package com.example.spendwise.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.spendwise.core.theme.LocalNavController

@Composable
fun BottomNavigationBar() {
    val navController = LocalNavController.current
    NavigationBar {
        val routesList = listOf(
            BottomNavItem.Home,
            BottomNavItem.Transactions,
            BottomNavItem.Budget,
            BottomNavItem.Other,
        )

        routesList.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(item.label)
                    )
                },
                label = { Text(stringResource(item.label)) },
                selected = navController.currentRoute == item.route.toString(),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}
