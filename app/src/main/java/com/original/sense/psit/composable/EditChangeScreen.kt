package com.original.sense.psit.composable

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.original.sense.psit.Authentication.phone
import com.original.sense.psit.ViewModels.EditProfileViewModel
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.ViewModels.truefalseViewModel
import com.original.sense.psit.model.PostModel.PostEditProfile
import com.original.sense.psit.screens.access
import com.original.sense.psit.ui.theme.poppins
import kotlinx.coroutines.delay


@Composable
fun EditChangeScreen(roomNumber: String , phoneNumber: String?) {

    val context: Context = LocalContext.current.applicationContext

    val tokenStoreViewModel: TokenStoreViewModel = hiltViewModel()

    val tfViewModel: truefalseViewModel = hiltViewModel()

    val showToast = remember { mutableStateOf(false) }
    val toastMessage = remember { mutableStateOf("") }

    val editProfileViewModel : EditProfileViewModel = hiltViewModel()

    val updateResponse by editProfileViewModel.updateUserProfile.observeAsState()

    LaunchedEffect(updateResponse) {
        updateResponse?.let { response ->
            Log.d("LaunchedEffect", "updateResponse: $updateResponse")
            if (!response.error)
            {
                tokenStoreViewModel.updateProfile(phoneNumber.toString(),roomNumber)
                showToast.value = true
                toastMessage.value = response.responseData.msg
                delay(800)
                tfViewModel.updateShow(false)
            }
            else{
                toastMessage.value = "${response.error}"
            }
        }
    }

    if (showToast.value) {
        Toast.makeText(LocalContext.current, toastMessage.value, Toast.LENGTH_SHORT).show()
        showToast.value = false
    }


    Card(
            modifier = Modifier
                .fillMaxWidth() ,
            shape = (RoundedCornerShape(30.dp)) ,
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
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
                    text = "Change Details" ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 5.dp , top = 20.dp) ,
                    fontSize = 25.sp ,
                    color = Color(0xFFF6F6F6) ,
                    fontFamily = poppins ,
                    fontWeight = FontWeight.Bold
                )

                var phoneNumber = changeValue("Enter Phone Number", phoneNumber.toString())
                var roomNumber = changeValue("Enter Room Number",roomNumber)


                Spacer(modifier = Modifier.padding(5.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Button(
                        onClick = {

                            val updatePost = PostEditProfile(phoneNumber.toLong(),roomNumber)

                            editProfileViewModel.updateUserProfile(access ,updatePost)


                        } ,
                        colors = ButtonDefaults.buttonColors(Color(0xFF3068de)) ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = "Change" , color = Color.White ,
                            fontSize = 20.sp ,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(25.dp))
            }
        }


}


@Composable
fun changeValue(str: String,txt:String): String {



    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf(txt) }


    val containerColor = Color(0xFF28292e)
    OutlinedTextField(

        value = text,
        leadingIcon = {
            Icon(
                Icons.Outlined.Create, contentDescription = "Username",
                tint = Color(0xFFA7A7A7))
        },
        onValueChange = { text = it },
        shape = RoundedCornerShape(30.dp) ,


        placeholder = { Text(text = str, color = Color(0xFFA7A7A7),
            fontFamily = poppins,fontSize = 16.sp) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ) ,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White ,
            unfocusedTextColor = Color.White,
            focusedContainerColor = containerColor ,
            unfocusedContainerColor = containerColor ,
            disabledContainerColor = containerColor ,
            focusedBorderColor =  Color.White,
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