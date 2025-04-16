package com.example.spendwise.domain.use_cases

import com.example.spendwise.data.providers.database.transactions.toUiModel
import com.example.spendwise.domain.repositories.TransactionsRepository
import com.example.spendwise.ui.transactions.transactions.components.TransactionItemUiModel
import javax.inject.Inject

class GetAllTransactionsUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {
    operator fun invoke(): List<TransactionItemUiModel> {
        return transactionsRepository.getAllTransactions().map { it.toUiModel() }
    }
}