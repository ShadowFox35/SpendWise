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
import com.example.spendwise.ui.transactions.add_transaction.AddTransactionsScreen
import com.example.spendwise.ui.transactions.transactions.TransactionsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationRoutes.HOME_ROUTE,
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) },
    ) {
        composable(BottomNavigationRoutes.HOME_ROUTE) {
            HomeScreen()
        }
        composable(BottomNavigationRoutes.TRANSACTION_ROUTE) {
            TransactionsScreen(navController = navController)
        }
        composable(BottomNavigationRoutes.BUDGET_ROUTE) {
            BudgetScreen()
        }
        composable(BottomNavigationRoutes.OTHER_ROUTE) {
            OtherScreen()
        }

        composable(AppRoutes.ADD_TRANSACTION_ROUTE) {
            AddTransactionsScreen(navController = navController)
        }
    }
}