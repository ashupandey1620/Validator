package com.original.sense.psit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun SignUpPage(navController: NavHostController , context: MainActivity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF383838) ,
                        Color(0xFF222228)
                    ),
                    start = Offset(0.0979f, 0f) ,
                    end = Offset(0.2064f, 0f)
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }

}