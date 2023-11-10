package com.original.sense.psit

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.original.sense.psit.screens.AddScreen
import com.original.sense.psit.screens.HomeScreen
import com.original.sense.psit.screens.ProfileScreen

@Composable
fun HomePage(navController: NavHostController = rememberNavController()) {
    BottomNavigationBar(navController = navController)
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = "HomeGraph",
        startDestination = "home"
    ) {
        composable(route ="home") {
            HomeScreen(navController)
        }
        composable(route = "track") {
            AddScreen(navController)
        }
        composable(route = "service") {
            ProfileScreen(navController)
        }
    }
}
