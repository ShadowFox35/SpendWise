package com.example.spendwise.domain.use_cases

import com.example.spendwise.domain.repositories.TransactionsRepository
import javax.inject.Inject

class DeleteTransactionByIdUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {
    operator fun invoke(transactionId: Int) {
        return transactionsRepository.deleteTransactionById(transactionId)
    }
}