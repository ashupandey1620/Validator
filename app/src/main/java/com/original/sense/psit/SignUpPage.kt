package com.original.sense.psit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    ) ,
                    start = Offset(0.0979f , 0f) ,
                    end = Offset(0.2064f , 0f)
                )
            ).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "Sign Up",
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight().
            padding(start = 5.dp, top = 10.dp),
            fontSize = 46.sp,
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraBold
            )

        Text(text = "Create an Account to start getting Permission Today",
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight().
                padding(top = 5.dp)
                .align(CenterHorizontally),
            fontSize = 17.sp,
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Light,

        )

        SimpleOutlinedTextFieldName()
        SimpleOutlinedTextFieldUsername()
        SimpleOutlinedTextFieldEmail()
        SimpleOutlinedTextFieldPhone()
        SimpleOutlinedTextFieldRoom()
        Terms()
        ContinueButton()
        AlreadyAccount()
    }

}

@Composable
fun AlreadyAccount() {
    TODO("Not yet implemented")
}

@Composable
fun ContinueButton() {
    TODO("Not yet implemented")
}

@Composable
fun Terms() {
    TODO("Not yet implemented")
}


@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldName() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Filled.Person, contentDescription = "Search", tint = Color(0xFF222228))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(25.dp) ,

        placeholder = { Text(text = "Name", color = Color.White) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ) ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color(0xFF383838),
            containerColor = Color(0xFF383838)),
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}


@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldUsername() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Filled.Person, contentDescription = "Username", tint = Color(0xFF222228))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(25.dp) ,


        placeholder = { Text(text = "Username", color = Color.White) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ) ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color(0xFF383838),
            containerColor = Color(0xFF383838)),
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}

@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldEmail() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Filled.Email, contentDescription = "Email", tint = Color(0xFF222228))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(25.dp) ,


        placeholder = { Text(text = "Email", color = Color.White) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ) ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color(0xFF383838),
            containerColor = Color(0xFF383838)),
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}

@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldPhone() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Outlined.Phone, contentDescription = "Phone", tint = Color(0xFF222228))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(25.dp) ,


        placeholder = { Text(text = "Phone Number", color = Color.White) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ) ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color(0xFF383838),
            containerColor = Color(0xFF383838)),
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}

@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldRoom() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Filled.Person, contentDescription = "Room", tint = Color(0xFF222228))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(25.dp) ,

        placeholder = { Text(text = "Room Number", color = Color.White) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ) ,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color(0xFF383838),
            containerColor = Color(0xFF383838)),
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}