package com.original.sense.psit.ui

import android.content.Context
import android.content.res.Resources
import android.text.Layout

import android.util.DisplayMetrics
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.estimateAnimationDurationMillis
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.original.sense.psit.MainActivity
import com.original.sense.psit.R
import kotlinx.coroutines.delay



@Composable
fun OnboardingScreen(navController: NavHostController , context: MainActivity) {

    val image1Visibility = remember{mutableStateOf(false)}
    val image2Visibility = remember{mutableStateOf(false)}
    val image3Visibility = remember{mutableStateOf(false)}
    val image4Visibility = remember {
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
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF383838),
                        Color(0xFF222228)
                    ),
                    start = Offset(0.0979f, 0f),
                    end = Offset(0.2064f, 0f)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        ) {

            val density = LocalDensity.current
            val offsetX = with(density) { 225.1.dp.roundToPx() }
            val offsetY = with(density) { -23.05.dp.roundToPx() }

//            if (image1Visibility.value) {
//                Image(
//                    painter = painterResource(R.drawable.ellipse_10),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .offset { IntOffset(offsetX, offsetY) }
//                        .size(width = 254.65.dp, height = 257.49.dp)
//                )
//            }

            this@Column.AnimatedVisibility(
                visible = image1Visibility.value,
                enter = fadeIn(animationSpec = tween(durationMillis = 5000)),
                modifier = Modifier
                    .offset { IntOffset(offsetX, offsetY) }
                    .size(width = 254.65.dp, height = 257.49.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ellipse_10),
                    contentDescription = null
                )
            }



            val offsetX2 = with(density) { 115.02.dp.roundToPx() }
            val offsetY2 = with(density) { 143.5.dp.roundToPx() }

            this@Column.AnimatedVisibility(
                visible = image2Visibility.value,
                enter = fadeIn(animationSpec = tween(durationMillis = 7500)),
                modifier = Modifier
                    .offset { IntOffset(offsetX2, offsetY2) }
                    .size(width = 93.8.dp, height = 93.8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ellipse_11),
                    contentDescription = null
                )
            }

            val offsetX3 = with(density) { 161.92.dp.roundToPx() }
            val offsetY3 = with(density) { 84.4.dp.roundToPx() }

            this@Column.AnimatedVisibility(
                visible = image3Visibility.value,
                enter = fadeIn(animationSpec = tween(durationMillis = 10500)),
                modifier = Modifier
                    .offset { IntOffset(offsetX3, offsetY3) }
                    .size(width = 27.18.dp, height = 27.18.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ellipse_11),
                    contentDescription = null
                )
            }



            val offsetX4 = with(density) { 196.61.dp.roundToPx() }
            val offsetY4 = with(density) { 55.12.dp.roundToPx() }

            this@Column.AnimatedVisibility(
                visible = image1Visibility.value,
                enter = fadeIn(animationSpec = tween(durationMillis = 13000)),
                modifier = Modifier
                    .offset { IntOffset(offsetX4, offsetY4) }
                    .size(width = 12.21.dp, height = 12.21.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.ellipse_10),
                    contentDescription = null
                )
            }


        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "SO | PSIT",
            fontFamily = FontFamily.Cursive,
            fontSize = 40.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
        )

        Button(onClick = {
            onBoardingIsFinished(context = context)
            navController.popBackStack()
            navController.navigate("signup_page")
        }) {

            Text(text = ">")

        }
    }
}



fun pxToDp(px: Double , context: Context): Int {
    val resources: Resources = context.resources
    val metrics = DisplayMetrics()
    resources.displayMetrics.apply { metrics.setTo(this) }

    val dpi = metrics.densityDpi
    return (px * (dpi / 160)).toInt()
}

private fun onBoardingIsFinished(context: MainActivity) {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()

}
