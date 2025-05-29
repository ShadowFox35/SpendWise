package com.example.spendwise.domain.repositories

import com.example.spendwise.data.providers.database.transactions.TransactionEntity

interface TransactionsRepository {
    fun getAllTransactions(): List<TransactionEntity>
    fun getTransactionById(transactionId: Int): TransactionEntity?
    fun addTransaction(transaction: TransactionEntity)
    fun updateTransaction(transaction: TransactionEntity)
    fun deleteTransactionById(transactionId: Int)
}