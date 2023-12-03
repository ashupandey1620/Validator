package com.original.sense.psit

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.NfcA
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.original.sense.psit.Authentication.ForgotPasswordScreen
import com.original.sense.psit.Authentication.ResendPasswordScreen
import com.original.sense.psit.Authentication.SignInScreen
import com.original.sense.psit.Authentication.SignUpPage
import com.original.sense.psit.SoPsit.SplashScreen
import com.original.sense.psit.screens.handleTechTag
import com.original.sense.psit.ui.OnboardingScreen
import com.original.sense.psit.ui.theme.SoPsitTheme
import java.io.IOException


class MainActivity : ComponentActivity() {

//    private var nfcAdapter: NfcAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
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
                            HomePage(this@MainActivity)
                        }


                    }
                }
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        enableNfcForegroundDispatch()
//    }
//    override fun onPause() {
//        super.onPause()
//        disableNfcForegroundDispatch()
//    }
//
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Toast.makeText(this, "data "+intent.getAction(), Toast.LENGTH_SHORT).show();
        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action || NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
            handleTechTag(intent,this)
        }
    }

//    private fun handleTechTag(intent: Intent) {
//        val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
//        val nfca = NfcA.get(tag)
//        if (nfca != null) {
//            try {
//                nfca.connect()
//
//                Toast.makeText(this,"Connected",Toast.LENGTH_SHORT).show()
//
//                // Read NFC-A tag data
//                val tagData = nfca.tag.id
//                if (tagData != null && tagData.size > 0) {
//                    val tagId: String = byteArrayToHexString(tagData)
//                    Toast.makeText(this,"$tagId",Toast.LENGTH_SHORT).show()
//                    Toast.makeText(this,"$tagData",Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//                Toast.makeText(this , "Error reading NFC-A tag" , Toast.LENGTH_SHORT).show()
//            } finally {
//                try {
//                    nfca.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//    }
//
//
//    private fun enableNfcForegroundDispatch() {
//        val intent = Intent(this , MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
//        nfcAdapter?.enableForegroundDispatch(
//            this ,
//            PendingIntent.getActivity(
//                this ,
//                0 ,
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) ,
//                PendingIntent.FLAG_MUTABLE
//            ) ,
//            null ,
//            null
//        )
//    }
//
//
//    private fun disableNfcForegroundDispatch() {
//        nfcAdapter?.disableForegroundDispatch(this)
//    }
//
//    private fun byteArrayToHexString(bytes: ByteArray): String {
//        val sb = StringBuilder()
//        for (b in bytes) {
//            sb.append(String.format("%02x" , b))
//        }
//        return sb.toString()
//    }

}

