package com.example.spendwise.data.providers.database.transactions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(transactions: List<TransactionEntity>)

    @Update
    fun update(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM transactions WHERE id = :id LIMIT 1")
    fun getById(id: Int): TransactionEntity?

    @Query("SELECT * FROM transactions")
    fun getAll(): List<TransactionEntity>
}