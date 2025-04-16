package com.example.spendwise.ui.transactions.add_transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spendwise.R
import com.example.spendwise.core.utils.rememberAddTransactionViewModelFactory
import com.example.spendwise.ui.transactions.add_transaction.model.AddTransactionEffect
import com.example.spendwise.ui.transactions.add_transaction.model.AddTransactionEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionsScreen(
    navController: NavController,
    transactionItemId: Int?
) {
    val factory = rememberAddTransactionViewModelFactory()
    val viewModel = viewModel { factory.create(transactionItemId) }
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                AddTransactionEffect.NavigateBack -> navController.popBackStack()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            windowInsets = WindowInsets(0.dp),
            modifier = Modifier.background(Color.Green),
            title = {
                Text(stringResource(state.screenTitleRes))
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.common_back)
                    )
                }
            },
            actions = {
                if (transactionItemId != null) {
                    IconButton(
                        onClick = {
                            viewModel.handleEvent(
                                AddTransactionEvent.OnDeleteButtonClick(
                                    transactionItemId
                                )
                            )
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.common_delete),
                        )
                    }
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.transactionTitle,
                onValueChange = { newValue ->
                    viewModel.handleEvent(AddTransactionEvent.OnTitleFieldChanged(newTitle = newValue))
                },
                label = { Text(stringResource(R.string.add_transaction_screen_title_field)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.transactionAmount,
                onValueChange = { newValue ->
                    viewModel.handleEvent(AddTransactionEvent.OnAmountFieldChanged(newAmount = newValue))
                },
                label = { Text(stringResource(R.string.add_transaction_screen_amount_field)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.handleEvent(
                        AddTransactionEvent.OnSubmitButtonClick(
                            transactionId = transactionItemId,
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.saveTransactionEnabled
            ) {
                Text(stringResource(R.string.add_transaction_screen_submit_button))
            }
        }
    }
}