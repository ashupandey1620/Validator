package com.original.sense.psit.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.original.sense.psit.R
import com.original.sense.psit.ViewModels.PsitViewModel
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.ui.theme.poppins


@Composable
fun ProfileScreen(navController: NavHostController) {


    val name = "Ashutosh Pandey "
    Box (modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())){


        Column(modifier = Modifier.fillMaxSize()) {
            
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                
                Box(modifier = Modifier
                    .size(80.dp)
                    ) {

                    Image(painter = painterResource(id = R.drawable.imageprofile), contentDescription = null,
                        modifier = Modifier.fillMaxSize())
                    
                }


                Box(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    ) {
                    Text(
                        text = "Mr. $name" ,
                        color = Color.White ,
                        fontSize = 17.sp ,
                        modifier = Modifier.padding(start = 20.dp),
                        fontFamily = poppins
                    )
                }
                
            }


            Text(text = "General",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = poppins,
                modifier = Modifier
                    .padding(top = 30.dp, start = 30.dp))

            ProfileCard(navController)


            Text(text = "Log Out",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = poppins,
                modifier = Modifier
                    .padding(top = 45.dp, start = 30.dp))


            logOutCard(navController)
        }



    }
}





@Composable
fun ProfileCard(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ){
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp),
            shape = RoundedCornerShape(20.dp) ,
            colors = CardDefaults.cardColors(Color(0xFF383841)),
            elevation = CardDefaults.cardElevation(2.dp)

        ) {
                Column {
                    SupportAccountItem(mainText = "Notifications",
                        onClick = {
                            navController.navigate("notification")
                        } )

                    SupportAccountItem(mainText = "Edit Profile",
                        onClick = {
                            navController.navigate("editProfile")
                        } )

                    SupportAccountChangeTheme(mainText = "Change Theme",
                        onClick = {

                        } )

                    SupportAccountItem(mainText = "Change Password",
                        onClick = {
                            navController.navigate("changePassword")
                        } )

                }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportAccountChangeTheme(mainText: String , onClick: () -> Unit) {

    var checkedState by remember { mutableStateOf(false) }


    Card(onClick = onClick,
        colors = CardDefaults.cardColors(Color(0xFF383841)),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp , horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {


                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = mainText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFfffffe),
                        fontFamily = poppins
                    )
                }
            }


           Switch(modifier = Modifier.size(20.dp).padding(end = 30.dp),checked = checkedState ,
               onCheckedChange = { checkedState = !checkedState },
               enabled = true,
           )
        }
    }
}


@Composable
fun logOutCard(navController: NavController) {


    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ){
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp),
            shape = RoundedCornerShape(20.dp) ,
            colors = CardDefaults.cardColors(Color(0xFF383841)),
            elevation = CardDefaults.cardElevation(2.dp)

        ) {

            Column(modifier = Modifier.height(65.dp),
                verticalArrangement = Arrangement.Center) {

              logoutAccountItem(mainText = "Sign Out",
                  onClick = {

                  },
                  navController)

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportAccountItem(mainText: String, onClick: () -> Unit) {

    Card(onClick = onClick,
        colors = CardDefaults.cardColors(Color(0xFF383841)),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp , horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
               

                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = mainText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFfffffe),
                        fontFamily = poppins
                    )
                }
            }


            Icon(tint = Color.Gray,
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_right_arrow)
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun logoutAccountItem(mainText: String , onClick: () -> Unit , navController: NavController) {

    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()

    val refreshToken by tokenStoreViewModel.readRefresh.collectAsState()

    val psitViewModel: PsitViewModel = hiltViewModel()

    val getLogout by psitViewModel.getStudent.observeAsState()



    getLogout.let { getLogout->
        Log.d("HAGEMARU","$getLogout")
        if (getLogout != null) {
            if (getLogout.errors){

            }
            else{

            }
        }
    }

    Card(onClick = {
        refreshToken?.let { psitViewModel.logOut(it) }


        navController.navigate("Auth_Graph")


                   },


        colors = CardDefaults.cardColors(Color(0xFF383841)),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp , horizontal = 14.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {


                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = mainText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFfffffe),
                        fontFamily = poppins
                    )
                }
            }


            Icon(tint = Color.Gray,
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.logout)
            )

        }
    }
}





