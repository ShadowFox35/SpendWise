package com.example.spendwise.ui.transactions.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spendwise.data.providers.database.AppDatabase.Companion.INSTANCE
import com.example.spendwise.data.providers.database.transactions.toUiModel
import com.example.spendwise.navigation.AddTransactionRoute
import com.example.spendwise.navigation.EditTransactionRoute
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEffect
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEvent
import com.example.spendwise.ui.transactions.transactions.model.TransactionsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel() {

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
                INSTANCE?.let { database ->
                    val transactions = database.transactionDao().getAll()
                    _state.value = TransactionsState(transactionsList = transactions.map {
                        it.toUiModel()
                    })
                }
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

class TransactionsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return TransactionsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}