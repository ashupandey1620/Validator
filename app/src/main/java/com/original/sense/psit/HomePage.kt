package com.original.sense.psit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.original.sense.psit.screens.AddScreen
import com.original.sense.psit.screens.HomeScreen
import com.original.sense.psit.screens.NotificationScreen
import com.original.sense.psit.screens.ProfileScreen


data class BottomNavigationItem(
    val route: String ,
    val title: String ,
    val selectedIcon: ImageVector ,
    val unselectedIcon: ImageVector ,
    val hasNews: Boolean ,
    val badgeCount: Int? = null
)
@Composable
fun HomePage(navController: NavHostController = rememberNavController()) {
    BottomNavigationBar(navController = navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationItem(
            route = "home",
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            route = "add",
            title = "Add",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),


        BottomNavigationItem(
            route = "profile",
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false,
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }


        Scaffold(
            
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            alwaysShowLabel = false,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route){
                                    popUpTo(item.route)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = { Text(text = item.title) },
                            icon = {
                                BadgedBox(badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else {
                                            item.unselectedIcon
                                        },
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ){paddingValues -> 
        MainPageNavigation(navController = navController)
        }
    }



@Composable
fun MainPageNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = "HomeGraph",
        startDestination = "home"
    ) {


            composable(route = "home") {
                HomeScreen(navController)
            }
            composable(route = "add") {
                AddScreen(navController)
            }
            composable(route = "profile") {
                ProfileScreen(navController)
            }


        composable(route = "notificationPage") {
            NotificationScreen()
        }





    }
}
