package com.example.spendwise.ui.transactions.transactions.model

import com.example.spendwise.domain.entities.TransactionEntity
import java.time.LocalDate

data class TransactionsState(
    val transactionsList: List<TransactionEntity>,
) {
    companion object {
        val Initial = TransactionsState(
            transactionsList = listOf(
                TransactionEntity(
                    title = "transaction1",
                    amount = 0.11,
                    date = LocalDate.of(2000, 6, 21),
                ), TransactionEntity(
                    title = "transaction2",
                    amount = 0.23,
                    date = LocalDate.of(2004, 7, 14),
                ), TransactionEntity(
                    title = "transaction3",
                    amount = 1.73,
                    date = LocalDate.of(2001, 2, 22),
                ), TransactionEntity(
                    title = "transaction4",
                    amount = 2.53,
                    date = LocalDate.of(2005, 1, 13),
                )
            ),
        )
    }
}