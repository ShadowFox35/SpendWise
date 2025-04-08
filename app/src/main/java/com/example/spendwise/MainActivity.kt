package com.example.spendwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.core.theme.LocalNavController
import com.example.spendwise.core.theme.SpendWiseTheme
import com.example.spendwise.data.providers.database.AppDatabase
import com.example.spendwise.navigation.BottomNavigationBar
import com.example.spendwise.navigation.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppDatabase.init(application)
        setContent {
            SpendWiseTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
                BottomNavigationBar(navController = navController)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavigationGraph()
        }
    }
}
