package com.example.spendwise.ui.transactions.add_transaction.model

import androidx.annotation.StringRes
import com.example.spendwise.R

data class AddTransactionState(
    @StringRes val screenTitleRes: Int = R.string.add_transaction_screen_title,
    val saveTransactionEnabled: Boolean = false,
    val transactionTitle: String = "",
    val transactionAmount: String = "",
)