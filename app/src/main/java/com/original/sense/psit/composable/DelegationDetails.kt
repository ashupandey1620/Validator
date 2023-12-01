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
import androidx.compose.foundation.layout.wrapContentWidth
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
fun DelegationDetails() {
    Card (shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(0xFF3068de)) ,


        ) {
            Column( modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()) {

    Row {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp , end = 30.dp , top = 30.dp) ,
        fontFamily = poppins ,
        text = "Delegation Details:" ,
        color = Color.White ,
        textAlign = TextAlign.Center ,
        fontSize = 20.sp
    )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp , end = 30.dp , top = 30.dp) ,
            fontFamily = poppins ,
            text = "hola" ,
            color = Color.White ,
            textAlign = TextAlign.Center ,
            fontSize = 20.sp
        )
    }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp , end = 30.dp , top = 30.dp) ,
                fontFamily = poppins ,
                text = "Assigner Details:" ,
                color = Color.White ,
                textAlign = TextAlign.Center ,
                fontSize = 20.sp
            )

            Row (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),horizontalArrangement = Arrangement.SpaceBetween){


                Column(  modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()) {


                    Box(
                        modifier = Modifier
                            .size(70.dp) ,
                        Alignment.BottomStart
                    )
                    {
                        Image(
                            modifier = Modifier.fillMaxSize() ,
                            painter = painterResource(id = R.drawable.grass) ,
                            contentDescription = ""
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .size(130.dp) ,

                ) {

                    Image(
                        modifier = Modifier.fillMaxSize() ,
                        painter = painterResource(id = R.drawable.thinkman) ,
                        contentDescription = ""
                    )


                }
            }

        }
    }
}