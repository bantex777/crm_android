package com.techshift.crm.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techshift.crm.ui.home.HomeScreen
import com.techshift.crm.ui.login.LoginScreen

@Composable
fun AppNavigation(paddingValues: PaddingValues) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
    ) {

        composable("login") {
            LoginScreen(paddingValues = paddingValues) {
                navController.navigate("home")
            }
        }

        composable("home") {
            HomeScreen(paddingValues)
        }
    }
}