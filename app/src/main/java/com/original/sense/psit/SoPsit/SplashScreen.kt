package com.original.sense.psit.SoPsit

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.original.sense.psit.MainActivity
import com.original.sense.psit.R
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.screens.access
import com.original.sense.psit.ui.theme.poppins
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController , context: MainActivity) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF222228)
    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = false
    )

    val alpha = remember {
        Animatable(0f)
    }


    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()

    val accessToken by tokenStoreViewModel.readAccess.collectAsState()

    accessToken?.let { str ->
        access = accessToken.toString()
    }


    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f,
            animationSpec = tween(300)
        )
        delay(1000)

        if(access!="") {
            navController.popBackStack()
            navController.navigate("HomeGraph")
        }
        else if (onBoardingIsFinished(context = context)) {
            navController.popBackStack()
            navController.navigate("signup_page")
        }
        else
         {
            navController.popBackStack()
            navController.navigate("Onboarding")
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF383838) ,
                        Color(0xFF222228)
                    ) ,
                    start = Offset(0.0979f , 0f) ,
                    end = Offset(0.2064f , 0f)
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

            Image(
                painter = painterResource(id = R.drawable.pic) ,
                contentDescription = null ,
                contentScale = ContentScale.Fit ,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(CircleShape),

                )


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "SO PSIT",
            fontFamily = poppins ,
            modifier = Modifier.alpha(alpha.value),
            fontSize = 33.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF2c53a7)
        )
    }
}

private fun onBoardingIsFinished(context: MainActivity): Boolean {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isFinished", false)

}
