package com.original.sense.psit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.original.sense.psit.R

@Composable
fun detailScreen(navController: NavController) {


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ,
            Arrangement.SpaceBetween
        ) {

            SearchView(
                placeHolder = "Search" ,
                modifier = Modifier
            )

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
    }


}