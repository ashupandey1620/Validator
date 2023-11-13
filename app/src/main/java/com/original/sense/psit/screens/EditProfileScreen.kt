package com.original.sense.psit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.original.sense.psit.R
import com.original.sense.psit.ui.theme.poppins

@Composable
fun EditProfileScreen(navController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())) {

        Row (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp)
            .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically){



            IconButton(onClick = { navController.popBackStack()
            } ){
                Icon(painter = painterResource(id = R.drawable.arrowleft) , contentDescription = null,
                    tint = Color.White)
            }

            Spacer(modifier = Modifier.padding(7.dp))
            
            Text(text = "Edit Profile",
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = poppins,
                )

        }

        CircularImage()

        NameColumn()

        EmailColumn()

        PhoneColumn()

        RoomColumn()

    }

}

@Composable
fun CircularImage() {

}

@Composable
fun RoomColumn() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "Room Number :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        EditProfileItemEditScreen(mainText = "D-31")
        {

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileItemEditScreen(mainText: String , content: @Composable () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(Color(0xFF46464b)),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp , horizontal = 10.dp),
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

            IconButton(onClick = {},modifier = Modifier.size(20.dp)) {
                Icon(tint = Color.White,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.pencil)
                )
            }




        }
    }
}

@Composable
fun PhoneColumn() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "Phone Number :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        EditProfileItemEditScreen(mainText = "8963784152")
        {

        }
    }

}


@Composable
fun NameColumn() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "Name :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))


        EditProfileItemMainScreen(mainText = "Ashutosh Pandey")
        {

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileItemMainScreen(mainText: String, onClick: () -> Unit) {

    Card(onClick = {},
        colors = CardDefaults.cardColors(Color(0xFF46464b)),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp , horizontal = 10.dp),
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
        }
    }
}


@Composable
fun EmailColumn() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "Email :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))


        EditProfileItemMainScreen(mainText = "ashupandey1620@gmail.com")
        {

        }
    }

}