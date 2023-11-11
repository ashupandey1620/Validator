package com.original.sense.psit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.original.sense.psit.R
import com.original.sense.psit.ui.theme.poppins

@Composable
fun ProfileScreen(navController: NavController) {


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

            ProfileCard()


            Text(text = "Log Out",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = poppins,
                modifier = Modifier
                    .padding(top = 45.dp, start = 30.dp))


            logOutCard()
        }



    }
}





@Composable
fun ProfileCard() {
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
                        onClick = {} )

                    SupportAccountItem(mainText = "Edit Profile",
                        onClick = {} )

                }




        }
    }
}



@Composable
fun logOutCard() {
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

                SupportAccountItem(mainText = "Sign Out",
                    onClick = {} )


            }




        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportAccountItem(mainText: String, onClick: () -> Unit) {

    Card(onClick = {},
        colors = CardDefaults.cardColors(Color(0xFF383841)),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp , horizontal = 14.dp),
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