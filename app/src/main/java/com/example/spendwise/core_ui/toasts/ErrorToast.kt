package com.example.spendwise.core_ui.toasts

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@Composable
fun ErrorToast(@StringRes messageResId: Int?) {
    val context = LocalContext.current

    if (messageResId != null) {
        val message = stringResource(messageResId)
        LaunchedEffect(messageResId) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}