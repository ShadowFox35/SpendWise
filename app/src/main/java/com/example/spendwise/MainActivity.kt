package com.example.spendwise

import com.example.spendwise.navigation.BottomNavigationBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.core.theme.SpendWiseTheme
import com.example.spendwise.navigation.AppRoutes
import com.example.spendwise.navigation.BottomNavigationRoutes
import com.example.spendwise.navigation.NavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendWiseTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val isBottomNavRoute = currentRoute in setOf(
        BottomNavigationRoutes.HOME_ROUTE,
        BottomNavigationRoutes.TRANSACTION_ROUTE,
        BottomNavigationRoutes.BUDGET_ROUTE,
        BottomNavigationRoutes.OTHER_ROUTE
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomNavRoute) {
                BottomNavigationBar(navController = navController)
            }
        },
        floatingActionButton = {
            if (isBottomNavRoute) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(AppRoutes.ADD_TRANSACTION_ROUTE)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Transaction"
                    )
                }
            }

        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController = navController)
        }
    }
}