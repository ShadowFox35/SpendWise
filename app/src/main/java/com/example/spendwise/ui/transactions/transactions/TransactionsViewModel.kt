package com.example.spendwise.ui.transactions.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.domain.use_cases.GetAllTransactionsUseCase
import com.example.spendwise.navigation.AddTransactionRoute
import com.example.spendwise.navigation.EditTransactionRoute
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEffect
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEvent
import com.example.spendwise.ui.transactions.transactions.model.TransactionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(TransactionsState(transactionsList = emptyList()))
    val state: StateFlow<TransactionsState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<TransactionsEffect>()
    val effect = _effect.asSharedFlow()

    init {
        getTransactions()
    }

    fun handleEvent(event: TransactionsEvent) {
        when (event) {
            is TransactionsEvent.Initial -> getTransactions()
            is TransactionsEvent.OnFloatActionButtonClick -> viewModelScope.launch {
                _effect.emit(
                    TransactionsEffect.OpenAddTransactionRouteWithOutArgs(
                        AddTransactionRoute
                    )
                )
            }

            is TransactionsEvent.OnEditActionButtonClick -> openAddTransactionScreen(event.itemId)
        }
    }

    private fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val transactions = getAllTransactionsUseCase()
                _state.value = TransactionsState(transactionsList = transactions)
            } catch (_: Exception) {
            }
        }
    }

    private fun openAddTransactionScreen(itemId: Int) {
        viewModelScope.launch {
            _effect.emit(
                TransactionsEffect.OpenAddTransactionScreen(
                    EditTransactionRoute(itemId)
                )
            )
        }
    }
}