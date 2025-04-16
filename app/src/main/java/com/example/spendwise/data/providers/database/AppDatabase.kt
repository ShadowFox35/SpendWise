package com.example.spendwise.data.providers.database

import com.example.spendwise.data.providers.database.transactions.TransactionDao
import com.example.spendwise.data.providers.database.transactions.TransactionEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TransactionEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {

        fun createInstance(context: Context):AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "my_database"
            ).build()
        }
    }
}
