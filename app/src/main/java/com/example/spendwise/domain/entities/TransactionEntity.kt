package com.example.spendwise.domain.entities

import java.time.LocalDate

data class TransactionEntity(
    val title: String,
    val amount: Double,
    val date: LocalDate
)

