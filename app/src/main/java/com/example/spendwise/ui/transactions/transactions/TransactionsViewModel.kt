package com.example.spendwise.ui.transactions.transactions

import androidx.lifecycle.viewModelScope
import com.example.spendwise.domain.use_cases.GetAllTransactionsUseCase
import com.example.spendwise.navigation.AppRoute
import com.example.spendwise.ui.common.model.BaseViewModel
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEffect
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEvent
import com.example.spendwise.ui.transactions.transactions.model.TransactionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
) : BaseViewModel<TransactionsState, TransactionsEvent, TransactionsEffect>() {

    override fun initialState(): TransactionsState =
        TransactionsState(transactionsList = emptyList())

    init {
        handleEvent(TransactionsEvent.Initial)
    }

    override fun handleEvent(event: TransactionsEvent) {
        when (event) {
            is TransactionsEvent.Initial -> onScreenOpened()
            is TransactionsEvent.OnFloatActionButtonClick -> onFloatActionButtonClick()
            is TransactionsEvent.OnEditActionButtonClick -> onEditActionButtonClick(event.itemId)
        }
    }

    private fun onScreenOpened() {
        getTransactions()
    }

    private fun onFloatActionButtonClick() {
        openAddTransactionScreen()
    }

    private fun onEditActionButtonClick(itemId: Int) {
        openEditTransactionScreen(itemId)
    }

    private fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            val transactions = getAllTransactionsUseCase()
            updateState { copy(transactionsList = transactions) }
        }
    }

    private fun openAddTransactionScreen() {
        viewModelScope.launch {
            submitEffect(
                TransactionsEffect.OpenAddTransactionScreen(
                    AppRoute.AddTransactionRoute
                )
            )
        }
    }

    private fun openEditTransactionScreen(itemId: Int) {
        viewModelScope.launch {
            submitEffect(
                TransactionsEffect.OpenEditTransactionScreen(
                    AppRoute.EditTransactionRoute(itemId)
                )
            )
        }
    }
}