package com.example.spendwise.ui.transactions.transactions.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spendwise.R
import com.example.spendwise.ui.transactions.transactions.model.TransactionsEvent

@Composable
fun TransactionsListItemRow(
    itemModel: TransactionItemUiModel,
    onEvent: (TransactionsEvent) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Create,
            contentDescription = "icon",
            modifier = Modifier
                .size(24.dp)
                .clickable { onEvent(TransactionsEvent.OnEditActionButtonClick(itemModel.id)) }
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = itemModel.date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )

                Text(
                    text = stringResource(R.string.transaction_screen_example,itemModel.amount),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = itemModel.title,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
