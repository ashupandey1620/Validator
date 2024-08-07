package com.original.sense.psit.Authentication

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import com.original.sense.psit.MainActivity
import com.original.sense.psit.R
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.ui.theme.poppins
import kotlinx.coroutines.delay

var emailResend = ""
@Composable
fun ResendPasswordScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF222228)
    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = false
    )

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
        tickVisibility.value = true
        image1Visibility.value = true
        show = true
        delay(200)
        image2Visibility.value = true
        delay(500)
        image3Visibility.value = true
        delay(500)
        image4Visibility.value = true



    }

    if (show) {
        BottomSheetDialog(
            onDismissRequest = {
                show = false
            } ,
            properties = BottomSheetDialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                dismissWithAnimation = false
            )
        ) {

            ForgotSheet(navController)

        }
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
                    .rotate(-44.13f)
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



            val offsetX5 = with(density) { 130.45.dp.roundToPx() }
            val offsetY5 = with(density) { 200.73.dp.roundToPx() }

            this@Column.AnimatedVisibility(
                visible = tickVisibility.value ,
                enter = fadeIn(animationSpec = tween(durationMillis = 5000)) ,
                modifier = Modifier
                    .offset { IntOffset(offsetX5 , offsetY5) }
                    .size(width = 100.dp , height = 100.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.tickl) ,
                    contentDescription = null
                )
            }


            val offsetX6 = with(density) { 167.55.dp.roundToPx() }
            val offsetY6 = with(density) { 170.46.dp.roundToPx() }

            this@Column.AnimatedVisibility(
                visible = tickVisibility.value ,
                enter = fadeIn(animationSpec = tween(durationMillis = 5000)) ,
                modifier = Modifier
                    .offset { IntOffset(offsetX6 , offsetY6) }
                    .size(width = 135.dp , height = 135.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.tickr) ,
                    contentDescription = null
                )
            }


        }


    }


}

@Composable
fun ForgotSheet(navController: NavHostController) {
    val context = LocalContext.current.applicationContext

    Card(modifier = Modifier
        .fillMaxWidth(),
        shape = (RoundedCornerShape(30.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.4f)
        )

    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(18.dp)
                .verticalScroll(rememberScrollState()) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Resend Password" ,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 5.dp , top = 20.dp) ,
                fontSize = 35.sp ,
                color = Color(0xFFF6F6F6) ,
                fontFamily = poppins ,
                fontWeight = FontWeight.Bold
            )


            emailResend = SimpleOutlinedTextFieldEmail2()



            Spacer(modifier = Modifier.padding(85.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                Button(
                    onClick = {

                    } ,
                    colors = ButtonDefaults.buttonColors(Color(0xFF3068de)) ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = "Send Password" , color = Color.White ,
                        fontSize = 20.sp ,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 10.dp) ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {

                    Text(
                        text = "Already a User? " ,
                        color = Color(0xFFF6F6F6) ,
                        fontFamily = poppins ,
                        fontSize = 12.sp
                    )

                    Text(text = "Sign In" ,
                        color = Color(0xFF3068de) ,
                        fontFamily = poppins ,
                        fontSize = 12.sp ,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                            navController.navigate("signIn_page")
                        })
                }
            }

        }
    }
}


@Composable
fun SimpleOutlinedTextFieldEmail2(): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    val containerColor = Color(0xFF28292e)
    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Outlined.Email, contentDescription = "Email", tint = Color(0xFFA7A7A7))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,


        placeholder = { Text(text = "Email",   fontFamily = poppins,
            color = Color(0xFFA7A7A7), fontSize = 16.sp) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Email
        ) ,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White ,
            focusedContainerColor = containerColor ,
            unfocusedContainerColor = containerColor ,
            disabledContainerColor = containerColor ,
            focusedBorderColor = Color.White ,
            unfocusedBorderColor = Color(0xFF383838) ,
        ) ,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 16.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                //


            }
        )

    )
    return text
}