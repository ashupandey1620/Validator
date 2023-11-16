package com.original.sense.psit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.original.sense.psit.ui.theme.poppins

@Composable
fun StudentProfile(navController: NavHostController) {


    val name = "Ayush Agnihotri"
    Box (modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())){


        Column(modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBackground())) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                Arrangement.SpaceBetween){

                Row( verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.popBackStack()
                    } ){
                        Icon(painter = painterResource(id = R.drawable.arrowleft) , contentDescription = null,
                            tint = Color.White)
                    }

                    Text(text = "Student Profile",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontFamily = poppins ,
                    )
                }


                Row {

                    CircularTapButton()
                    Spacer(modifier = Modifier.padding(6.dp))
                    CircularNotificationButton()
                }

            }

            CircularImage(name)

            NameColumn()

            CollegeRollNumber()

            AktuRollNumber()

            ClassStudent()

            Branch()

            TypeStudent()


        }
    }


}

@Composable
fun TypeStudent() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "Type :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        EditProfileItemMainScreen(mainText = "Day Scholar")
        {

        }
    }
}


@Composable
fun Branch() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "Branch :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        EditProfileItemMainScreen(mainText = "Computer Science and Engineering: Core")
        {

        }
    }
}

@Composable
fun ClassStudent() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "Class :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        EditProfileItemMainScreen(mainText = "CS-3A Elite")
        {

        }
    }
}

@Composable
fun AktuRollNumber() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "Aktu Roll Number :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        EditProfileItemMainScreen(mainText = "2101641530046")
        {

        }
    }
}

@Composable
fun CollegeRollNumber() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(text = "College Roll Number :",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppins,
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        EditProfileItemMainScreen(mainText = "31560")
        {

        }
    }
}
