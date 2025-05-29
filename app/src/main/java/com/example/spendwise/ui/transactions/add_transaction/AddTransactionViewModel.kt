package com.example.spendwise.ui.transactions.add_transaction

import androidx.lifecycle.viewModelScope
import com.example.spendwise.R
import com.example.spendwise.data.providers.database.transactions.TransactionEntity
import com.example.spendwise.domain.use_cases.AddTransactionUseCase
import com.example.spendwise.domain.use_cases.DeleteTransactionByIdUseCase
import com.example.spendwise.domain.use_cases.GetTransactionByIdUseCase
import com.example.spendwise.domain.use_cases.UpdateTransactionUseCase
import com.example.spendwise.ui.common.model.BaseViewModel
import com.example.spendwise.ui.transactions.add_transaction.model.AddTransactionEffect
import com.example.spendwise.ui.transactions.add_transaction.model.AddTransactionEvent
import com.example.spendwise.ui.transactions.add_transaction.model.AddTransactionState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId

class AddTransactionViewModel @AssistedInject constructor(
    @Assisted private val transactionItemId: Int?,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val deleteTransactionByIdUseCase: DeleteTransactionByIdUseCase,
) : BaseViewModel<AddTransactionState, AddTransactionEvent, AddTransactionEffect>() {

    override fun initialState(): AddTransactionState =
        AddTransactionState()

    init {
        handleEvent(AddTransactionEvent.Initial)
    }

    override fun handleEvent(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.Initial -> onScreenOpened()
            is AddTransactionEvent.OnDateEditButtonClick -> onDateEditButtonClick()
            is AddTransactionEvent.OnDateFieldChanged -> onDateFieldChanged(event.newDate)
            is AddTransactionEvent.OnTitleFieldChanged -> onTitleFieldChanged(event.newTitle)
            is AddTransactionEvent.OnAmountFieldChanged -> onAmountFieldChanged(event.newAmount)
            is AddTransactionEvent.OnSubmitButtonClick -> onSubmitButtonClick(event.transactionId)
            is AddTransactionEvent.OnDeleteButtonClick -> deleteTransaction(event.transactionId)
        }
    }

    private fun onScreenOpened() {
        transactionItemId?.let {
            setScreenTitle()
            getTransactionInfo(transactionItemId)
        }
    }

    private fun setScreenTitle() {
        updateState { copy(screenTitleRes = R.string.edit_transaction_screen_title) }
    }


    private fun getTransactionInfo(itemId: Int?) {

        if (itemId == null) return

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val transaction = getTransactionByIdUseCase(itemId)
                if (transaction != null) {
                    updateState {
                        copy(
                            transactionTitle = transaction.title,
                            transactionAmount = transaction.amount
                        )
                    }
                }
            } catch (_: Exception) {
                updateState { copy(errorMessage = R.string.error_get_transaction) }
            }
        }
    }

    private fun onDateEditButtonClick() {
        updateState { copy(showDatePicker = !state.value.showDatePicker) }
    }

    private fun onDateFieldChanged(newDate: Long?) {
        if (newDate == null) return
        val selectedDate =
            Instant.ofEpochMilli(newDate).atZone(ZoneId.systemDefault())
                .toLocalDate()
        updateState { copy(transactionDate = selectedDate, showDatePicker = false) }
        validateSaveTransaction()
    }


    private fun onTitleFieldChanged(newTitle: String) {
        updateState { copy(transactionTitle = newTitle) }
        validateSaveTransaction()
    }

    private fun onAmountFieldChanged(newAmount: String) {
        updateState { copy(transactionAmount = newAmount) }
        validateSaveTransaction()
    }

    private fun validateSaveTransaction() {
        updateState { copy(saveTransactionEnabled = this.transactionTitle.isNotEmpty() && this.transactionAmount.isNotEmpty()) }
    }

    private fun onSubmitButtonClick(transactionId: Int?) {
        if (transactionId == null) {
            addTransaction()
        } else {
            updateTransaction(id = transactionId)
        }
    }

    private fun addTransaction() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                addTransactionUseCase(
                    TransactionEntity(
                        title = state.value.transactionTitle,
                        amount = state.value.transactionAmount.toDouble(),
                        date = state.value.transactionDate
                    )
                )
                submitEffect(AddTransactionEffect.NavigateBack)
            }
        } catch (_: Exception) {
            updateState { copy(errorMessage = R.string.error_add_transaction) }
        }
    }

    private fun updateTransaction(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                updateTransactionUseCase(
                    transactionEntity = TransactionEntity(
                        id = id,
                        title = state.value.transactionTitle,
                        amount = state.value.transactionAmount.toDouble(),
                        date = state.value.transactionDate,
                    ),
                )
                submitEffect(AddTransactionEffect.NavigateBack)
            }
        } catch (_: Exception) {
            updateState { copy(errorMessage = R.string.error_update_transaction) }
        }
    }

    private fun deleteTransaction(id: Int) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                deleteTransactionByIdUseCase(id)
                submitEffect(AddTransactionEffect.NavigateBack)
            }
        } catch (_: Exception) {
            updateState { copy(errorMessage = R.string.error_delete_transaction) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(transactionItemId: Int?): AddTransactionViewModel
    }
}

