package com.original.sense.psit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.original.sense.psit.screens.GradientBackground
import kotlinx.coroutines.delay

@Composable
fun SignInScreen(navController: NavHostController , context: MainActivity) {



    val image1Visibility = remember{ mutableStateOf(false) }
    val image2Visibility = remember{ mutableStateOf(false) }
    val image3Visibility = remember{ mutableStateOf(false) }
    val image4Visibility = remember {
        mutableStateOf(false)
    }
    val tickVisibility = remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = 0) {
        image1Visibility.value = true
        delay(500)
        image2Visibility.value = true
        delay(500)
        image3Visibility.value = true
        delay(500)
        image4Visibility.value = true
//        delay(500)
//        tickVisibility.value = true
    }


    var alignment by remember {
        mutableStateOf(Alignment.CenterHorizontally)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBackground()),
        horizontalAlignment = alignment
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {

            val density = LocalDensity.current
            val offsetX = with(density) { -43.63.dp.roundToPx() }
            val offsetY = with(density) { -4.12.dp.roundToPx() }


            this@Column.AnimatedVisibility(
                visible = image1Visibility.value ,
                enter = fadeIn(animationSpec = tween(durationMillis = 5000)) ,
                modifier = Modifier
                    .offset { IntOffset(offsetX , offsetY) }
                    .size(width = 168.47.dp , height = 168.47.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ellipse_13) ,
                    contentDescription = null
                )
            }


            val offsetX2 = with(density) { 370.06.dp.roundToPx() }
            val offsetY2 = with(density) { 147.29.dp.roundToPx() }

            this@Column.AnimatedVisibility(
                visible = image2Visibility.value ,
                enter = fadeIn(animationSpec = tween(durationMillis = 5000)) ,
                modifier = Modifier
                    .offset { IntOffset(offsetX2 , offsetY2) }
                    .size(width = 52.5.dp , height = 52.5.dp)
//                    .rotate(-44.13f)
            ) {
                Image(modifier = Modifier.rotate(-44.13f),
                    painter = painterResource(R.drawable.ellipse_11) ,
                    contentDescription = null
                )
            }

            val offsetX3 = with(density) { 315.23.dp.roundToPx() }
            val offsetY3 = with(density) { 231.1.dp.roundToPx() }

            this@Column.AnimatedVisibility(
                visible = image3Visibility.value ,
                enter = fadeIn(animationSpec = tween(durationMillis = 5000)) ,
                modifier = Modifier
                    .offset { IntOffset(offsetX3 , offsetY3) }
                    .size(width = 22.39.dp , height = 22.39.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ellipse_12) ,
                    contentDescription = null
                )
            }


            val offsetX4 = with(density) { 249.71.dp.roundToPx() }
            val offsetY4 = with(density) { 265.25.dp.roundToPx() }

            this@Column.AnimatedVisibility(
                visible = image1Visibility.value ,
                enter = fadeIn(animationSpec = tween(durationMillis = 5000)) ,
                modifier = Modifier
                    .offset { IntOffset(offsetX4 , offsetY4) }
                    .size(width = 275.39.dp , height = 278.46.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ellipse_10) ,
                    contentDescription = null
                )
            }


        }


    }

}