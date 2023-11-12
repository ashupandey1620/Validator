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

    }

}