package com.original.sense.psit

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.original.sense.psit.Authentication.ForgotPasswordScreen
import com.original.sense.psit.Authentication.ResendPasswordScreen
import com.original.sense.psit.Authentication.SignInScreen
import com.original.sense.psit.Authentication.SignUpPage
import com.original.sense.psit.SoPsit.SplashScreen
import com.original.sense.psit.ViewModels.PsitViewModel
import com.original.sense.psit.ViewModels.StudentListViewModel
import com.original.sense.psit.ViewModels.ViewModelFactory
import com.original.sense.psit.model.PersonModel
import com.original.sense.psit.screens.handleTechTag
import com.original.sense.psit.ui.OnboardingScreen
import com.original.sense.psit.ui.theme.SoPsitTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var studentListViewModel: StudentListViewModel
    private lateinit var psitViewModel: PsitViewModel // Remove the direct injection of PsitViewModel


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        psitViewModel = ViewModelProvider(this).get(PsitViewModel::class.java) // Obtain PsitViewModel instance

        studentListViewModel = ViewModelProvider(this, ViewModelFactory(psitViewModel)).get(StudentListViewModel::class.java)

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
                            startDestination = "signup_page"
                        ){

                            composable("signIn_page"){
                                SignInScreen(navController = navController, context = this@MainActivity)
                            }

                            composable("signup_page"){
                                SignUpPage(navController = navController, context = this@MainActivity)
                            }

                            composable("forget_page") {
                                ForgotPasswordScreen(navController = navController, context = this@MainActivity)
                            }

                            composable("resend_password_page") {
                                ResendPasswordScreen(navController = navController, context = this@MainActivity)
                            }
                        }

                        composable("HomeGraph") {
                            HomePage(this@MainActivity,studentListViewModel)
                        }


                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action || NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            handleTechTag(intent, this) { studentId ->
                // Access your ViewModel and add the student ID to the studentList
                // Retrieve your ViewModel using Hilt or ViewModelProvider
                Log.d("NARAYAN",studentId)
                // Add the student ID to the studentList in ViewModel
                studentListViewModel.addStudent(studentId.toLong())

                Log.d("NARAYAN",studentListViewModel.studentList.toList().toString())

            }
        }
    }

}

