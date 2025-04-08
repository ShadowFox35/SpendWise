package com.example.spendwise.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TransactionModel(
    val id: Int,
    val title: String,
    val amount: Double,
    val date: String
)



