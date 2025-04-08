package com.example.spendwise.ui.transactions.add_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spendwise.data.providers.database.AppDatabase.Companion.INSTANCE
import com.example.spendwise.data.providers.database.transactions.TransactionEntity
import com.example.spendwise.data.providers.database.transactions.toUiModel
import com.example.spendwise.ui.transactions.add_transaction.model.AddTransactionEffect
import com.example.spendwise.ui.transactions.add_transaction.model.AddTransactionEvent
import com.example.spendwise.ui.transactions.add_transaction.model.AddTransactionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddTransactionViewModel(itemId: Int?) : ViewModel() {
    private val _state = MutableStateFlow(AddTransactionState())
    val state: StateFlow<AddTransactionState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AddTransactionEffect>()
    val effect = _effect.asSharedFlow()

    init {
        itemId?.let { getTransactionInfo(itemId) }
    }

    fun handleEvent(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.OnTitleFieldChanged -> onTitleFieldChanged(event.newTitle)
            is AddTransactionEvent.OnAmountFieldChanged -> onAmountFieldChanged(event.newAmount)
            is AddTransactionEvent.OnSubmitButtonClick -> onSubmitButtonClick(event.transactionId)
            is AddTransactionEvent.OnDeleteButtonClick -> deleteTransaction(event.transactionId)
        }
    }

    private fun getTransactionInfo(itemId: Int) {


        viewModelScope.launch(Dispatchers.IO) {
            try {
                INSTANCE?.let {
                    val transaction = INSTANCE?.transactionDao()?.getById(id = itemId)?.toUiModel()
                    if (transaction != null) {
                        _state.update { it.copy(transactionTitle = transaction.title) }
                        _state.update { it.copy(transactionAmount = transaction.amount) }
                    }
                }
            } catch (_: Exception) {
            }
        }
    }


    private fun onTitleFieldChanged(newTitle: String) {
        _state.update { it.copy(transactionTitle = newTitle) }
        validateSaveTransaction()
    }

    private fun onAmountFieldChanged(newAmount: String) {
        _state.update { it.copy(transactionAmount = newAmount) }
        validateSaveTransaction()
    }

    private fun validateSaveTransaction() {
        _state.update { it.copy(saveTransactionEnabled = it.transactionTitle.isNotEmpty() && it.transactionAmount.isNotEmpty()) }
    }

    private fun onSubmitButtonClick(transactionId: Int?) {
        if (transactionId == null) {
            addTransaction(
                title = _state.value.transactionTitle,
                amount = _state.value.transactionAmount.toDouble(),
            )
        } else {
            updateTransaction(
                id = transactionId,
                title = _state.value.transactionTitle,
                amount = _state.value.transactionAmount.toDouble(),
            )
        }
    }

    private fun addTransaction(title: String, amount: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            INSTANCE?.transactionDao()?.add(
                transaction = TransactionEntity(
                    title = title,
                    amount = amount,
                    date = LocalDate.now().toString()
                )
            )

            _effect.emit(AddTransactionEffect.NavigateBack)
        }
    }

    private fun updateTransaction(id: Int, title: String, amount: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            INSTANCE?.transactionDao()?.updateById(
                id = id,
                title = title,
                amount = amount,
            )
            _effect.emit(
                AddTransactionEffect.NavigateBack
            )
        }
    }

    private fun deleteTransaction(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            INSTANCE?.transactionDao()?.deleteById(id)
        }
    }
}

class AddTransactionViewModelFactory(
    private val itemId: Int?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTransactionViewModel(itemId) as T
    }
}
