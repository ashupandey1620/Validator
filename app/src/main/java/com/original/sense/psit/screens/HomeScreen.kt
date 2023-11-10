package com.original.sense.psit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {


    Box (modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())){

    }



}

@Composable
fun GradientBackground(): Brush {

    val colors = listOf(
        Color(0xFF383838),
        Color(0xFF222228),
        Color(0xFF222228),
        Color(0xFF222228),
        Color(0xFF222228))

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )
}