package com.example.spendwise.data.di

import android.content.Context
import com.example.spendwise.data.providers.database.AppDatabase
import com.example.spendwise.data.repository_impls.TransactionsRepositoryImpl
import com.example.spendwise.domain.repositories.TransactionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):AppDatabase {
        return AppDatabase.createInstance(context)
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(appDatabase: AppDatabase): TransactionsRepository {
        return TransactionsRepositoryImpl(appDatabase)
    }
}