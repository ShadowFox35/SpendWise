package com.example.spendwise.ui.transactions.add_transaction.model

import androidx.annotation.StringRes
import com.example.spendwise.R
import java.time.LocalDate

data class AddTransactionState(
    @StringRes val screenTitleRes: Int = R.string.add_transaction_screen_title,
    @StringRes val errorMessage: Int? = null,
    val saveTransactionEnabled: Boolean = false,
    val showDatePicker: Boolean = false,
    val transactionDate: LocalDate = LocalDate.now(),
    val transactionTitle: String = "",
    val transactionAmount: String = "",
)