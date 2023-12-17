package com.original.sense.psit.Authentication

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.original.sense.psit.MainActivity
import com.original.sense.psit.ViewModels.PsitViewModel
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.model.PostModel.TempRegisterPost
import com.original.sense.psit.ui.theme.poppins
import com.shashank.sony.fancytoastlib.FancyToast


var name  = ""
var userName = ""
var email = ""
var phone = ""
var room = ""


@Composable
fun SignUpPage(navController: NavHostController , context: MainActivity) {



    var dialogVisible by remember { mutableStateOf(false) }

    var valid : Boolean
    val psitViewModel : PsitViewModel = hiltViewModel()

    val registerResponse by psitViewModel.registrationStatus.observeAsState()

    registerResponse?.let { response ->
        Log.d("Registerola -Response","$response")
        Log.d("Registerola -error","${response.error}")
        Log.d("Registerola -responseData","${response.responseData}")
        Log.d("Registerola -","${response.message?.email}")
        Log.d("Registerola -statusCode","${response.statusCode}")

    }



    if(registerResponse?.error == true)
    {
        Toast.makeText(context,"Error occurred",Toast.LENGTH_LONG).show()
    }
    else
    {
        Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBackground())
            .padding(18.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 5.dp , top = 20.dp),
            fontSize = 48.sp,
            color = Color(0xFFF6F6F6),
            fontFamily = poppins,
            fontWeight = FontWeight.ExtraBold
            )
        Text(text = "Create an Account to start getting Permission Today",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 15.dp , start = 5.dp)
                .align(CenterHorizontally),
            fontSize = 18.sp,
            color =  Color(0xFFA7A7A7),
            fontFamily = poppins,
            fontWeight = FontWeight.Light,
            softWrap = true
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        name      =   SimpleOutlinedTextFieldName()
        userName  =   SimpleOutlinedTextFieldUsername()
        email     =   SimpleOutlinedTextFieldEmail()
        phone     =   SimpleOutlinedTextFieldPhone()
        room      =   SimpleOutlinedTextFieldRoom()

        Terms()


        Row (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()){

            Button(onClick = {

                val signUpPost = TempRegisterPost(
                    email,
                    name,
                    userName,
                    phone.toLong(),
                    room) // Create LoginPost data class or object

                psitViewModel.registerUser(signUpPost)

                Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show()

            },
                colors = ButtonDefaults.buttonColors(Color(0xFF3068de)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)){
                Text(text = "Continue" , color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(8.dp))
            }
        }



        AlreadyAccount(navController)
    }


    if(dialogVisible) {
        AlertDialog(
                containerColor = Color(0xFF383841) ,
                shape = RoundedCornerShape(30.dp) ,
                onDismissRequest = { dialogVisible = false } ,
                title = { Text("Registered!!", color = Color.White) } ,
                text = { Text("You will receive a mail in some time with Credentials for Sign In", color = Color.White) } ,
                confirmButton = {
                    Button(
                        onClick = {

                        }
                    ) {
                        Text("Okay")
                    }
                } ,
            )
    }



}

fun checkValidity(
    name: String ,
    userName: String ,
    email: String ,
    phone: String ,
    room: String ,
    context: MainActivity
): Boolean {
    if (name.any { it.isDigit() })
    {
      Toast.makeText(context,"Name Should not Contains Digit",Toast.LENGTH_LONG).show()
        return false
    }
    else if (name.isEmpty())
    {
        Toast.makeText(context,"Name Can't be null",Toast.LENGTH_LONG).show()
        return false
    }
    else if(email.length>200){
        Toast.makeText(context,"email not valid",Toast.LENGTH_LONG).show()
        return false
    }
    else if (email.isEmpty())
    {
        Toast.makeText(context,"Email Can't be null",Toast.LENGTH_LONG).show()
        return false
    }
    else if(phone.length>10){
        Toast.makeText(context,"Phone Number length greater than 10",Toast.LENGTH_LONG).show()
        return false
    }
    else if (phone.isEmpty())
    {
        Toast.makeText(context,"Phone Number Can't be null",Toast.LENGTH_LONG).show()
        return false
    }
    else if (room.length>10){
        Toast.makeText(context,"Room Number length greater than 10",Toast.LENGTH_LONG).show()
        return false
    }
    else if (room.isEmpty())
    {
        Toast.makeText(context,"Room Number Can't be null",Toast.LENGTH_LONG).show()
        return false
    }

    return true
}

@Composable
fun AlreadyAccount(navController: NavHostController) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .width(20.dp)
        .padding(top = 10.dp),
       horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = "Already have an account?",
            color = Color(0xFF5C5D63),
            modifier = Modifier.padding(start = 15.dp),
            fontSize = 13.sp,
            fontWeight = FontWeight.Light,
            fontFamily = poppins,
            )

        TextButton(onClick = { navController.navigate("signIn_page") }, modifier = Modifier.padding(start = 3.dp)) {
            Text(text = "Log In",
                color = Color(0xFF757575),
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                fontFamily = poppins,
            )
        }

    }
}



@Composable
fun Terms() {
    Row (modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(10.dp)
        .padding(top = 10.dp)){

        MyCheckedState()

        val annotatedString = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xFF5C5D63) ,
                    fontSize = 13.sp
                )
            ) {
                append("I agree to the")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(0xFF888888) ,
                    fontSize = 13.sp
                )
            ) {
                append(" Terms of Service ")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(0xFF5C5D63),
                    fontSize = 13.sp
                )
            ) {
                append("and")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(0xFF888888),
                    fontSize = 13.sp
                )
            ) {
                append(" Privacy Policy")
            }

        }

        Text(text = annotatedString,
            color = Color.White,
            fontSize = 15.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Light)
        
        
    }
}


@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldName(): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    val containerColor = Color(0xFF383838)
    OutlinedTextField(
        value = text,
        leadingIcon = {
            Icon(Icons.Outlined.Person, contentDescription = "Search", tint = Color(0xFFA7A7A7))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,

        placeholder = { Text(text = "Name", color =Color(0xFFA7A7A7), fontSize = 16.sp)},
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
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


@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldUsername(): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    val user = arrayOf("Ashutosh","Satvik","Sanat","Ayush","Rishab")

    val containerColor = Color(0xFF383838)
    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Outlined.Person, contentDescription = "Username",
                tint = Color(0xFFA7A7A7))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,


        placeholder = { Text(text = "Username", color = Color(0xFFA7A7A7),
            fontFamily = poppins,fontSize = 16.sp) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ) ,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White ,
            focusedContainerColor = containerColor ,
            unfocusedContainerColor = containerColor ,
            disabledContainerColor = containerColor ,
            focusedBorderColor = if(text.isEmpty()) Color.White
            else if(user.contains(text))
                Color.Red
            else
                Color.Green ,
            //Color(0xFF64bf75),
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

@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldEmail(): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current.applicationContext


    val containerColor = Color(0xFF383838)
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
            imeAction = ImeAction.Next,
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




@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldPhone(): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    val containerColor = Color(0xFF383838)
    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Outlined.Phone, contentDescription = "Phone", tint = Color(0xFFA7A7A7))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,


        placeholder = { Text(text = "Phone Number",  fontFamily = poppins,
            color = Color(0xFFA7A7A7), fontSize = 16.sp) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ) ,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White ,
            focusedContainerColor = containerColor ,
            unfocusedContainerColor = containerColor ,
            disabledContainerColor = containerColor ,
            focusedBorderColor = if (text.length<10) Color.White else Color(0xFFe10012) ,
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
                // do something here
            }
        )
    )

    return text
}

@OptIn(ExperimentalComposeUiApi::class , ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldRoom(): String {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current.applicationContext

    val containerColor = Color(0xFF383838)
    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(Icons.Outlined.Place, contentDescription = "Room", tint = Color(0xFFA7A7A7))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,

        placeholder = { Text(text = "Room Number",  fontFamily = poppins,
            color = Color(0xFFA7A7A7), fontSize = 16.sp) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
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
                // do something here

                Toast.makeText(context,"Toast Message from the text", Toast.LENGTH_SHORT).show()

                Toast.makeText(context,"$text",Toast.LENGTH_LONG).show()

            }
        )

    )

    return text
}

@Composable
fun MyCheckedState() {
    var checkedState by remember { mutableStateOf(false) }
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


@Composable
fun CircleCheckbox(selected: Boolean, enabled: Boolean = true, onChecked: () -> Unit) {

    val color = MaterialTheme.colorScheme
    val imageVector = if (selected) Icons.Filled.CheckCircle else Icons.Outlined.AccountCircle
    val tint = if (selected) color.primary.copy(alpha = 0.8f) else Color.White
    val background = if (selected) Color.White else Color.Transparent

    IconButton(onClick = { onChecked() },
        modifier = Modifier.offset(x = 4.dp, y = 4.dp),
        enabled = enabled) {

        Icon(imageVector = imageVector, tint = tint,
            modifier = Modifier.background(background, shape = CircleShape),
            contentDescription = "checkbox")
    }
}


