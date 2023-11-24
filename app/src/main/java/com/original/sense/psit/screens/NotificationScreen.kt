package com.original.sense.psit.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.original.sense.psit.R
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.ui.theme.poppins

@Composable
fun NotificationScreen(navController: NavHostController) {

    val text = "Delegation removed for Lectures " +
            "5th & 6th  for 7th December 2023 from Rishabh Didwania."

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())) {


        Row (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp)
            .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically){



            IconButton(onClick = { navController.popBackStack()
            } ){
                Icon(painter = painterResource(id = R.drawable.arrowleft) , contentDescription = null,
                    tint = Color.White)
            }

            Spacer(modifier = Modifier.padding(2.dp))

            Text(text = "Notifications",
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = poppins ,
            )



        }

        NotificationBox(text = text)
        NotificationBox(text = text)
    }
}

@Composable
fun NotificationBox(text: String) {

    Column(modifier = Modifier.padding(10.dp)){


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight() , shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Color(0xFF4d4d52)),
            border = BorderStroke(1.dp, Color.White)
        )

        {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp)
            ) {


                Text(
                    text = text ,
                    color = Color.White ,
                    fontSize = 13.sp ,
                    fontFamily = poppins ,
                )


            }
        }
    }
}


