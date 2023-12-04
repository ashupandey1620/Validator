package com.original.sense.psit.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.original.sense.psit.R
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.ui.theme.poppins

const val name = "Ashutosh Pandey"
@Composable
fun detailScreen(navController: NavController) {


    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ,
            Arrangement.SpaceBetween
        ) {

            FrontLobe(modifier = Modifier)

            Row {

                Box(modifier = Modifier.size(50.dp)) {

                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
//                            .clickable {
//                                show = true
//                            }
                        ,
                                painter = painterResource(id = R.drawable.tap) ,
                        contentDescription = ""
                    )

                }

                Spacer(modifier = Modifier.padding(6.dp))
                CircularNotificationButton(navController)
            }
        }

        DateAndCalendar()

        DateLazyList()

        AllowedLectures()



    }


}

@Composable
fun DateAndCalendar() {

}


@Composable
fun DateLazyList() {

}

@Composable
fun AllowedLectures() {

}



@Composable
fun FrontLobe( modifier: Modifier) {

    Row (
        modifier
            .fillMaxWidth(0.7f)
            .height(50.dp)){

        Box(modifier = Modifier.size(50.dp)) {

            Card(modifier = Modifier.fillMaxSize() ,
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color(0xFF383841))) {


                Column(
                    modifier = Modifier.fillMaxSize() ,
                    Arrangement.Center ,
                    Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "AS" ,
                        color = Color.White ,
                        fontSize = 20.sp ,
                        fontFamily = poppins ,
                    )
                }
            }

        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
            verticalArrangement = Arrangement.SpaceBetween) {

            Text(text = name,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = poppins ,
            )

            Text(text = "2101641530046",
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = poppins ,
            )

        }


    }

}
