package com.example.spendwise.data.providers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.spendwise.data.providers.database.transactions.TransactionDao
import com.example.spendwise.data.providers.database.transactions.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class TransactionsDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {

        fun createInstance(context: Context):TransactionsDatabase {
            return Room.databaseBuilder(
                context,
                TransactionsDatabase::class.java,
                "transactions_database"
            ).build()
        }
    }
}
