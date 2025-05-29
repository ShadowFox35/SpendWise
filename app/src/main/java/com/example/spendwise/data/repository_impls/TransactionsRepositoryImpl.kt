package com.example.spendwise.data.repository_impls

import com.example.spendwise.data.providers.database.TransactionsDatabase
import com.example.spendwise.data.providers.database.transactions.TransactionEntity
import com.example.spendwise.domain.repositories.TransactionsRepository
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val database: TransactionsDatabase,
) : TransactionsRepository {

    override fun getAllTransactions(): List<TransactionEntity> {
        return database.transactionDao().getAll()
    }

    override fun getTransactionById(transactionId: Int): TransactionEntity? {
        return database.transactionDao().getById(transactionId)
    }

    override fun addTransaction(transaction: TransactionEntity) {
        database.transactionDao().add(transaction)
    }

    override fun updateTransaction(transaction: TransactionEntity) {
        database.transactionDao().update(transaction)
    }

    override fun deleteTransactionById(transactionId: Int) {
        database.transactionDao().deleteById(transactionId)
    }
}