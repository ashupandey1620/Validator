package com.original.sense.psit.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.original.sense.psit.Authentication.SignInPagePassword
import com.original.sense.psit.ViewModels.PsitViewModel
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.model.PostModel.ChangePasswordPost
import com.original.sense.psit.ui.theme.poppins


var currentPassword = ""
var newPassword1 = ""
var newPassword2 = ""
var access = ""

@Composable
fun ChangePassword(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF222228)
    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = false
    )
    val context = LocalContext.current.applicationContext

    val psitViewModel : PsitViewModel = hiltViewModel()

    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()

    val accessToken by tokenStoreViewModel.readAccess.collectAsState()

    val changePasswordResponse by psitViewModel.changePassword.observeAsState()

    accessToken?.let { str ->
        access = accessToken.toString()
    }

    changePasswordResponse?.let { response ->
        Log.d("Change Password-Response","$response")
        Log.d("Change Password-error","${response.error}")
        Log.d("Change Password-responseData","${response.responseData}")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())
        ,verticalArrangement = Arrangement.Center) {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = (RoundedCornerShape(30.dp)) ,
            colors = CardDefaults.cardColors(
                containerColor = Color.Black.copy(alpha = 0.4f)
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(18.dp)
                    .verticalScroll(rememberScrollState()) ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Change Password" ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 5.dp , top = 20.dp) ,
                    fontSize = 25.sp ,
                    color = Color(0xFFF6F6F6) ,
                    fontFamily = poppins ,
                    fontWeight = FontWeight.Bold
                )

                currentPassword = SignInPagePassword("Current Password")
                newPassword1 = SignInPagePassword("New Password")
                newPassword2 = SignInPagePassword("New Password")


                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 10.dp) ,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = "Forgot Current Password?" ,
                        color = Color(0xFFF6F6F6) ,
                        fontFamily = poppins ,
                        fontSize = 12.sp ,
                        modifier = Modifier.clickable {
                               navController.navigate("forget_page")
                        })
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Button(
                        onClick = {

//                            Toast.makeText(context , currentPassword , Toast.LENGTH_SHORT).show()
//                            Toast.makeText(context , newPassword1 , Toast.LENGTH_SHORT).show()
//                            Toast.makeText(context , newPassword2 , Toast.LENGTH_SHORT).show()

                            val changePasswordResponse = ChangePasswordPost(
                                currentPassword,
                                newPassword1,
                                newPassword2
                            ) // Create LoginPost data class or object

                            psitViewModel.changePassword(access,changePasswordResponse)
                        } ,
                        colors = ButtonDefaults.buttonColors(Color(0xFF3068de)) ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = "Change Password" , color = Color.White ,
                            fontSize = 20.sp ,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}