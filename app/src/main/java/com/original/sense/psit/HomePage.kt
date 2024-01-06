package com.original.sense.psit

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.original.sense.psit.Authentication.ForgotPasswordScreen
import com.original.sense.psit.Authentication.ResendPasswordScreen
import com.original.sense.psit.Authentication.SignInScreen
import com.original.sense.psit.Authentication.SignUpPage
import com.original.sense.psit.ViewModels.StudentListViewModel
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.screens.AddScreen
import com.original.sense.psit.screens.ChangePassword
import com.original.sense.psit.screens.EditProfileScreen
import com.original.sense.psit.screens.HomeScreen
import com.original.sense.psit.screens.NotificationScreen
import com.original.sense.psit.screens.ProfileScreen
import com.original.sense.psit.screens.StudentProfile
import com.original.sense.psit.screens.detailScreen
import kotlinx.coroutines.flow.first


data class BottomNavigationItem(
    val route: String ,
    val title: String ,
    val selectedIcon: ImageVector ,
    val unselectedIcon: ImageVector ,
    val hasNews: Boolean ,
    val badgeCount: Int? = null
)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(activity: Activity,studentListViewModel: StudentListViewModel, navController: NavHostController = rememberNavController()) {
    BottomNavigationBar(navController = navController,activity,studentListViewModel)
}




@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(navController: NavHostController,activity: Activity,studentListViewModel: StudentListViewModel) {

    val items = listOf(
        BottomNavigationItem(
            route = "home",
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ) ,
        BottomNavigationItem(
            route = "add",
            title = "Add",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ) ,


        BottomNavigationItem(
            route = "profile",
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false,
        ) ,
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
            bottomBar = {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination  = navBackStackEntry?.destination

    val bottomDestination = ((currentDestination?.route == "home") 
            || (currentDestination?.route == "add")
            || (currentDestination?.route == "profile"))
    
    if (bottomDestination) {
        NavigationBar(containerColor = Color(0xFF1b1c1f)) {
            items.forEachIndexed { index , item ->

                NavigationBarItem(

                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        indicatorColor = Color(0xFF2B59b8),
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White
                    ),

                    selected = selectedItemIndex == index,

                    alwaysShowLabel = false,

                    onClick = {
                        selectedItemIndex = index
                        navController.navigate(item.route) {
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
                                } ,
                                contentDescription = item.title
                            )
                        }
                    }
                )
            }
        }
    } 

}
        ){
        MainPageNavigation(navController = navController,activity,studentListViewModel)
        }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPageNavigation(navController: NavHostController,activity: Activity,studentListViewModel: StudentListViewModel) {

    val context = LocalContext.current.applicationContext // Get the context

    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()

    val accessToken by tokenStoreViewModel.readAccess.collectAsState()

    val startPage = if (accessToken=="") "signIn_page" else "home"


    NavHost(
        navController = navController,
        route = "HomeGraph",
        startDestination = startPage
    ) {

            composable(route = "home") {
                HomeScreen(navController,activity,studentListViewModel)
            }
            composable(route = "add") {
                AddScreen(navController,studentListViewModel)
            }
            composable(route = "profile") {
                ProfileScreen(navController)
            }

            composable(route = "notification") {
                NotificationScreen(navController)
            }

            composable(route = "editProfile") {
                EditProfileScreen(navController)
            }

            composable(route = "studentProfileInfo/{rollNum}",
                arguments = listOf(
                    navArgument(name = "rollNum"){
                        type = NavType.LongType
                    },
                ))
                 { rollNum ->
                StudentProfile(
                    navController ,
                    rollNum.arguments?.getLong("rollNum")
                )
            }

            composable(route = "detailedScreen/{rollNum}/{name}",
                arguments = listOf(
                    navArgument(name = "rollNum"){
                        type = NavType.LongType
                    },
                    navArgument(name = "name"){
                        type = NavType.StringType
                    }
                )
            ) {rollNum ->
                detailScreen(navController,
                    rollNum.arguments?.getLong("rollNum"),
                    rollNum.arguments?.getString("name"))
            }

            composable(route = "changePassword") {
                ChangePassword(navController)
            }


        /**
         *
         *
         *
         * Auth Graph Composables
         *
         *
         *
         */



        composable("signIn_page"){
            SignInScreen(navController = navController)
        }

        composable("signup_page"){
            SignUpPage(navController = navController)
        }

        composable("forget_page") {
            ForgotPasswordScreen(navController = navController)
        }

        composable("resend_password_page") {
            ResendPasswordScreen(navController = navController)
        }
    }
}
