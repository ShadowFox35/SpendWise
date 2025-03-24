package com.example.spendwise.ui.transactions.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spendwise.navigation.AppRoutes
import com.example.spendwise.navigation.Route
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEffect
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEvent
import com.example.spendwise.ui.transactions.transactions.model.TransactionsState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel() {
    private val _state = MutableStateFlow(TransactionsState.Initial)
    val state: StateFlow<TransactionsState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<TransactionsEffect>()
    val effect = _effect.asSharedFlow()

    fun handleEvent(event: TransactionsEvent) {
        when (event) {
            TransactionsEvent.OnFloatActionButtonClick -> openAddTransactionScreen()
        }
    }

    private fun openAddTransactionScreen() {
        viewModelScope.launch {
            _effect.emit(TransactionsEffect.OpenAddTransactionScreen(Route(AppRoutes.ADD_TRANSACTION_ROUTE)))
        }
    }
}

class TransactionsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}