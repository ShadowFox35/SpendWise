package com.example.spendwise.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.spendwise.core.theme.LocalNavController
import com.example.spendwise.domain.models.TransactionModel
import com.example.spendwise.ui.budget.BudgetScreen
import com.example.spendwise.ui.home.HomeScreen
import com.example.spendwise.ui.other.OtherScreen
import com.example.spendwise.ui.transactions.add_transaction.AddTransactionsScreen
import com.example.spendwise.ui.transactions.transactions.TransactionsScreen
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import kotlin.reflect.typeOf

@OptIn(InternalSerializationApi::class)
@Composable
fun NavigationGraph() {
    val navController = LocalNavController.current as NavHostController

    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) },
    ) {
        composable<HomeRoute> { HomeScreen() }
        composable<TransactionRoute> { TransactionsScreen() }
        composable<BudgetRoute> { BudgetScreen() }
        composable<OtherRoute> { OtherScreen() }

        composable<EditTransactionRoute>(
            typeMap = mapOf(
                typeOf<TransactionModel>() to CustomNavType(TransactionModel::class.serializer()),

                )
        ) { backStackEntry ->
            val args: EditTransactionRoute = backStackEntry.toRoute()
            AddTransactionsScreen(navController = navController, args.transactionItemId)
        }

        composable<AddTransactionRoute>(
        ) {
            AddTransactionsScreen(navController = navController, null)
        }
    }
}
