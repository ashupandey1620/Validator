package com.original.sense.psit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.original.sense.psit.ui.theme.poppins


@Composable
fun AddStudentScreen() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF3068de)),
        horizontalAlignment = Alignment.CenterHorizontally){
        
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp , end = 30.dp , top = 30.dp),
            fontFamily = poppins,
            text = "Ready to Add Another Student ?",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 23.sp
            )

        Box (modifier = Modifier
            .size(250.dp)
            .background(Color.Red)
            .padding(top = 20.dp)){

        }

        Row (modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(50.dp)

            ,
            horizontalArrangement = Arrangement.SpaceAround){

            Button(modifier = Modifier
                .height(50.dp)
                .width(125.dp),
                onClick = { /*TODO*/ },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color(0xFFffffff))
                ) {
                Text(text = "No", color = Color.Black)
                
            }

            Button(modifier = Modifier
                .height(50.dp)
                .width(125.dp) ,
                onClick = { /*TODO*/ },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color(0xFF00d084))
            ) {
                Text(text = "Yes", color = Color.White)

            }


        }

    }
    
}