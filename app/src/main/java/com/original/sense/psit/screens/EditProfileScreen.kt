package com.original.sense.psit.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.original.sense.psit.R
import com.original.sense.psit.ui.theme.poppins

@Composable
fun EditProfileScreen(navController: NavController) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)){

            Icon(painter = painterResource(id = R.drawable.logout) , contentDescription = null)
            
            Spacer(modifier = Modifier.padding(20.dp))
            
            Text(text = "Edit Profile",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = poppins
                )

        }

    }

}