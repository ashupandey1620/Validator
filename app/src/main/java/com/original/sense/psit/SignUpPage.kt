package com.original.sense.psit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.original.sense.psit.screens.GradientBackground
import com.original.sense.psit.ui.theme.poppins

@Composable
fun SignUpPage(navController: NavHostController , context: MainActivity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBackground())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "Sign Up",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 5.dp , top = 20.dp),
            fontSize = 46.sp,
            color = Color.White,
            fontFamily = poppins,
            fontWeight = FontWeight.ExtraBold
            )

        Text(text = "Create an Account to start getting Permission Today",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 15.dp,start = 5.dp)
                .align(CenterHorizontally),
            fontSize = 17.sp,
            color = Color.White,
            fontFamily = poppins,
            fontWeight = FontWeight.Light,

        )

        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        SimpleOutlinedTextFieldName()
        SimpleOutlinedTextFieldUsername()
        SimpleOutlinedTextFieldEmail()
        SimpleOutlinedTextFieldPhone()
        SimpleOutlinedTextFieldRoom()
        Terms()


        Row (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()){

            Button(onClick = {
                navController.popBackStack()
                navController.navigate("HomeGraph")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)){
                Text(text = "Continue" , color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp))
            }
        }

        AlreadyAccount()
    }

}

@Composable
fun AlreadyAccount() {
    Row (modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(15.dp),
       horizontalArrangement = Arrangement.Center){
        Text(text = "Already have an account?",
            color = Color.White,
            modifier = Modifier.padding(start = 15.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
            fontFamily = poppins,
            )
        Text(text = "Log In",
            color = Color.White,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Light,
            fontFamily = poppins,
        )
    }
}

@Composable
fun ContinueButton() {

}

@Composable
fun Terms() {
    Row (modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(10.dp)
        .padding(top = 10.dp)){

        MyCheckedState()

        Text(text = "I agree to the Terms of Service and Privacy Policy",
            color = Color.White,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 15.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Light)
        
        
    }
}


@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldName() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Outlined.Person, contentDescription = "Search", tint = Color(0xFFffffff))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,

        placeholder = { Text(text = "Name", color = Color.White, fontSize = 20.sp)},
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
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(top = 20.dp),
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
            Icon(Icons.Outlined.Person, contentDescription = "Username", tint = Color(0xFFffffff))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,


        placeholder = { Text(text = "Username", color = Color.White,   fontFamily = poppins,fontSize = 20.sp) },
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
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(top = 20.dp),
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
            Icon(Icons.Outlined.Email, contentDescription = "Email", tint = Color(0xFFffffff))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,


        placeholder = { Text(text = "Email",   fontFamily = poppins,color = Color.White, fontSize = 20.sp) },
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
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(top = 20.dp),
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
            Icon(Icons.Outlined.Phone, contentDescription = "Phone", tint = Color(0xFFffffff))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,


        placeholder = { Text(text = "Phone Number",  fontFamily = poppins, color = Color.White, fontSize = 20.sp) },
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
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(top = 20.dp),
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
            Icon(Icons.Outlined.Place, contentDescription = "Room", tint = Color(0xFFffffff))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,

        placeholder = { Text(text = "Room Number",  fontFamily = poppins, color = Color.White, fontSize = 20.sp) },
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
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(top = 20.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}

@Composable
fun MyCheckedState() {
    var checkedState by remember { mutableStateOf(false) }
//    CircularCheckBox(
//        checked = checkedState,
//        onCheckedChange = { checkedState = !checkedState }
//    )
//
    Checkbox(checked = checkedState , onCheckedChange = { checkedState = !checkedState })
}

@Composable
fun CircularCheckBox(
    checked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onCheckedChange() }
    ) {
        Icon(
            imageVector = if (checked) Icons.Outlined.CheckCircle else Icons.Rounded.Check,
            contentDescription = "Checkbox",
            tint = Color.White
        )
    }
}