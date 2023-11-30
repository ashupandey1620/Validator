package com.original.sense.psit.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun GradientBackground(): Brush {

    val colorsDark = listOf(
        Color(0xFF383838) ,
        Color(0xFF222228) ,
        Color(0xFF222228) ,
        Color(0xFF222228) ,
        Color(0xFF222228)
    )

    val colorsBright = listOf(
        Color(0xFFf8efe0) ,
        Color(0xFFfffaf1) ,
        Color(0xFFfffaf1) ,
        Color(0xFFe6d8bf) ,
        Color(0xFFe6d8bf) ,
    )

    return Brush.linearGradient(
        colors = colorsBright,
        start = Offset.Zero,
        end = Offset(Float.POSITIVE_INFINITY,
            Float.POSITIVE_INFINITY)
    )
}
