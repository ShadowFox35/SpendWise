package com.example.spendwise.domain.use_cases

import com.example.spendwise.data.providers.database.transactions.toUiModel
import com.example.spendwise.domain.repositories.TransactionsRepository
import com.example.spendwise.ui.transactions.transactions.components.TransactionItemUiModel
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {
    operator fun invoke(transactionId: Int): TransactionItemUiModel? {
        return transactionsRepository.getTransactionById(transactionId)?.toUiModel()
    }
}