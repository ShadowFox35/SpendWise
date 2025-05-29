package com.example.spendwise.ui.transactions.add_transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spendwise.R
import com.example.spendwise.core.utils.rememberAddTransactionViewModelFactory
import com.example.spendwise.core_ui.toasts.ErrorToast
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
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                AddTransactionEffect.NavigateBack -> navController.popBackStack()
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ErrorToast(messageResId = state.errorMessage)
        if (state.showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    viewModel.submitEvent(AddTransactionEvent.OnDateEditButtonClick)
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.submitEvent(
                            AddTransactionEvent.OnDateFieldChanged(datePickerState.selectedDateMillis)
                        )
                    }) {
                        Text(stringResource(R.string.common_submit))
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        viewModel.submitEvent(AddTransactionEvent.OnDateEditButtonClick)
                    }) {
                        Text(stringResource(R.string.common_cansel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        TopAppBar(
            windowInsets = WindowInsets(0.dp),
            title = { Text(stringResource(state.screenTitleRes)) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.common_back)
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        viewModel.submitEvent(AddTransactionEvent.OnDateEditButtonClick)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = stringResource(R.string.edit_transaction_screen_date),
                    )
                }

                if (transactionItemId != null) {
                    IconButton(
                        onClick = {
                            viewModel.submitEvent(
                                AddTransactionEvent.OnDeleteButtonClick(transactionItemId)
                            )
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
                    viewModel.submitEvent(AddTransactionEvent.OnTitleFieldChanged(newTitle = newValue))
                },
                label = { Text(stringResource(R.string.add_transaction_screen_title_field)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.transactionAmount,
                onValueChange = { newValue ->
                    viewModel.submitEvent(AddTransactionEvent.OnAmountFieldChanged(newAmount = newValue))
                },
                label = { Text(stringResource(R.string.add_transaction_screen_amount_field)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.submitEvent(
                        AddTransactionEvent.OnSubmitButtonClick(transactionId = transactionItemId)
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

