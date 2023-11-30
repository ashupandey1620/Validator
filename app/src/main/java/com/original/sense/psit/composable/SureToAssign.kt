package com.original.sense.psit.composable

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.original.sense.psit.R
import com.original.sense.psit.ui.theme.poppins

@Preview
@Composable
fun SureToAssign() {
    Card (shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(0xFF3068de)) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp , end = 30.dp , top = 30.dp) ,
                fontFamily = poppins ,
                text = "Are you sure to Assign these Lectures ?" ,
                color = Color.White ,
                textAlign = TextAlign.Center ,
                fontSize = 23.sp
            )

            Box(
                modifier = Modifier
                    .size(250.dp) ,
                contentAlignment = Alignment.Center
            ) {

                Image(
                    modifier = Modifier.fillMaxSize() ,
                    painter = painterResource(id = R.drawable.dustbingirl) ,
                    contentDescription = ""
                )

            }

            Row(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .height(50.dp) ,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(125.dp) ,
                    elevation = ButtonDefaults.buttonElevation(4.dp) ,
                    onClick = {


                    } ,
                    shape = RoundedCornerShape(10.dp) ,
                    colors = ButtonDefaults.buttonColors(Color(0xFFffffff))
                ) {
                    Text(text = "No" , color = Color.Black , fontSize = 25.sp)

                }

                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(125.dp) ,
                    elevation = ButtonDefaults.buttonElevation(4.dp) ,
                    onClick = {


                    } ,
                    shape = RoundedCornerShape(10.dp) ,
                    colors = ButtonDefaults.buttonColors(Color(0xFF00d084))
                ) {
                    Text(text = "Yes" , color = Color.White , fontSize = 25.sp)

                }

            }

            Spacer(modifier = Modifier.padding(20.dp))

        }

    }
}