package com.original.sense.psit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.original.sense.psit.ui.OnboardingScreen
import com.original.sense.psit.ui.theme.SoPsitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoPsitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize() ,
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController , startDestination = "Splash") {
                        composable("Splash") {
                            SplashScreen(navController = navController, context = this@MainActivity)
                        }
                        composable("Onboarding") {
                            OnboardingScreen(navController = navController, context = this@MainActivity)
                        }
                        navigation(
                            route = "Auth_Graph",
                            startDestination = "login_page"
                        ){
                            composable("login_page"){
                                LoginPage(navController = navController, context = this@MainActivity)
                            }
                            composable("register_page"){
                                SignUpPage(navController = navController, context = this@MainActivity)
                            }
                            composable("reset_page") {
                                ResetPage(navController = navController, context = this@MainActivity)
                            }
                        }
                        composable("HomeGraph") {
                            HomePage()
                        }
                    }
                }
            }
        }
    }
}

