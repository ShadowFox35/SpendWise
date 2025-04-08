package com.example.spendwise.ui.transactions.transactions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spendwise.core.theme.LocalNavController
import com.example.spendwise.ui.transactions.transactions.components.TransactionsList
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEffect
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEvent
import com.example.spendwise.ui.transactions.transactions.model.TransactionsState

@Composable
fun TransactionsScreen() {
    val navController = LocalNavController.current
    val viewModel: TransactionsViewModel = viewModel(factory = TransactionsViewModelFactory())
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleEvent(TransactionsEvent.Initial)
        viewModel.effect.collect { effect ->
            when (effect) {
                is TransactionsEffect.OpenAddTransactionScreen -> navController.navigate(effect.route)
                is TransactionsEffect.OpenAddTransactionRouteWithOutArgs -> navController.navigate(
                    effect.route
                )
            }
        }
    }

    TransactionContent(
        state = state,
        onNewEvent = viewModel::handleEvent,
    )
}

@Composable
private fun TransactionContent(
    state:TransactionsState,
    onNewEvent: (TransactionsEvent)->Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TransactionsList(state = state, onEvent = onNewEvent)
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 8.dp,
                    bottom = 24.dp
                ),
            onClick = {
               onNewEvent(TransactionsEvent.OnFloatActionButtonClick)
            },
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Transaction"
            )
        }
    }
}