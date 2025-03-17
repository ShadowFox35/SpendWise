package com.example.spendwise.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spendwise.ui.budget.BudgetScreen
import com.example.spendwise.ui.home.HomeScreen
import com.example.spendwise.ui.other.OtherScreen
import com.example.spendwise.ui.transactions.TransactionsScreen
import com.example.spendwise.ui.transactions.add_transaction.AddTransactionsScreen

const val ROUTE_ADD_TRANSACTION = "add_transaction"

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        enterTransition = { fadeIn(animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) },
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Transactions.route) {
            TransactionsScreen()
        }
        composable(BottomNavItem.Budget.route) {
            BudgetScreen()
        }
        composable(BottomNavItem.Other.route) {
            OtherScreen()
        }

        composable(ROUTE_ADD_TRANSACTION) { AddTransactionsScreen() }
    }
}