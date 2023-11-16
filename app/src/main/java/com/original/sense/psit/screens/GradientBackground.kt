package com.original.sense.psit.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun GradientBackground(): Brush {

    val colors = listOf(
        Color(0xFF383838) ,
        Color(0xFF222228) ,
        Color(0xFF222228) ,
        Color(0xFF222228) ,
        Color(0xFF222228)
    )

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(Float.POSITIVE_INFINITY,
            Float.POSITIVE_INFINITY)
    )
}
