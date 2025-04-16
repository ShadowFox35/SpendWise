package com.example.spendwise.domain.use_cases

import com.example.spendwise.data.providers.database.transactions.TransactionEntity
import com.example.spendwise.domain.repositories.TransactionsRepository
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {
    operator fun invoke(transactionEntity: TransactionEntity) {
        transactionsRepository.addTransaction(transactionEntity)
    }
}