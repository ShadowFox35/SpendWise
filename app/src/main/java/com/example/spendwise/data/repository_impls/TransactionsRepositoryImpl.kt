package com.example.spendwise.data.repository_impls

import com.example.spendwise.data.providers.database.AppDatabase
import com.example.spendwise.data.providers.database.transactions.TransactionEntity
import com.example.spendwise.domain.repositories.TransactionsRepository
import javax.inject.Inject

class TransactionsRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
) : TransactionsRepository {

    override fun getAllTransactions(): List<TransactionEntity> {
        return database.transactionDao().getAll()
    }

    override fun getTransactionById(transactionId: Int):
            TransactionEntity? {
        return database.transactionDao().getById(transactionId)
    }

    override fun addTransaction(transaction: TransactionEntity) {
        database.transactionDao().add(transaction)
    }

    override fun updateTransactionById(transaction: TransactionEntity) {
        database.transactionDao().updateById(transaction.id, transaction.title, transaction.amount)
    }

    override fun deleteTransactionById(transactionId: Int) {
        database.transactionDao().deleteById(transactionId)
    }
}