package com.example.spendwise.data.providers.database.transactions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(transactions: List<TransactionEntity>)

    @Query("UPDATE transactions SET title = :title, amount = :amount WHERE id = :id")
    fun updateById(id: Int, title: String, amount: Double)

    @Query("DELETE FROM transactions WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM transactions WHERE id = :id LIMIT 1")
    fun getById(id: Int): TransactionEntity?

    @Query("SELECT * FROM transactions")
    fun getAll(): List<TransactionEntity>
}