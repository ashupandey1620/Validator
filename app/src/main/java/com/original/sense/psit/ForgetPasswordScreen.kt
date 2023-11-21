package com.original.sense.psit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.original.sense.psit.screens.GradientBackground
import kotlinx.coroutines.delay

@Composable
fun ForgetPasswordScreen(navController: NavHostController , context: MainActivity) {


    var show by remember {
        mutableStateOf(false)
    }

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
        show = true
        delay(200)
        image2Visibility.value = true
        delay(500)
        image3Visibility.value = true
        delay(500)
        image4Visibility.value = true
//        delay(500)
//        tickVisibility.value = true

    }

}