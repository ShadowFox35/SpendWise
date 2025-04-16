package com.example.spendwise.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.spendwise.ui.transactions.add_transaction.AddTransactionViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Composable
inline fun <reified T> rememberEntryPoint(): T {
    val context = LocalContext.current
    return EntryPoints.get(context.applicationContext, T::class.java)
}

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface ViewModelFactoryEntryPoint {
    fun getAddTransactionViewModelFactory(): AddTransactionViewModel.Factory
}

@Composable
internal fun rememberAddTransactionViewModelFactory(): AddTransactionViewModel.Factory {
    val entryPoint = rememberEntryPoint<ViewModelFactoryEntryPoint>()
    return remember {
        entryPoint.getAddTransactionViewModelFactory()
    }
}
