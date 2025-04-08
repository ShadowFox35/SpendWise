package com.example.spendwise.ui.transactions.transactions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spendwise.R
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEvent
import com.example.spendwise.ui.transactions.transactions.model.TransactionsState

@Composable
fun TransactionsList(
    state: TransactionsState,
    onEvent: (TransactionsEvent) -> Unit
) {
    if (state.transactionsList.isEmpty()) EmptyTransactionsPlaceholder()
    else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(state.transactionsList) { item ->
                TransactionsListItemRow(
                    item,
                    onIconClick = { onEvent(TransactionsEvent.OnEditActionButtonClick(item.id)) }
                )
            }
        }
    }
}

@Composable
fun EmptyTransactionsPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.empty_list),
            contentDescription = stringResource(R.string.transaction_screen_empty),
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.transaction_screen_empty),
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}