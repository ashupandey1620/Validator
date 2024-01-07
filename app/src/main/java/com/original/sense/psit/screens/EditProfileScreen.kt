package com.original.sense.psit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties


import com.original.sense.psit.R
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.ViewModels.truefalseViewModel
import com.original.sense.psit.composable.EditChangeScreen
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.ui.theme.poppins
import java.util.Locale


@Composable
fun EditProfileScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF222228)
    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = false
    )

//    var show by remember { mutableStateOf(false) }

    val tfViewModel: truefalseViewModel = hiltViewModel()

    val context = LocalContext.current.applicationContext
    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()

    val name by tokenStoreViewModel.readName.collectAsState()
    val email by tokenStoreViewModel.readEmail.collectAsState()

    val phoneNumber by tokenStoreViewModel.readPhoneNo.collectAsState()
    val room by tokenStoreViewModel.readRoomNo.collectAsState()


    var show = tfViewModel.show.value

    if (show) {
        BottomSheetDialog(
            onDismissRequest = {
                tfViewModel.updateShow(false)
            },
            properties = BottomSheetDialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                dismissWithAnimation = true
            )
        ){
            room?.let { EditChangeScreen(it ,phoneNumber) }
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())
        .verticalScroll(rememberScrollState())) {

        Row (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp)
            .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically){



            IconButton(onClick = { navController.popBackStack()
            } ){
                Icon(painter = painterResource(id = R.drawable.arrowleft) , contentDescription = null,
                    tint = Color.White)
            }

            Spacer(modifier = Modifier.padding(7.dp))
            
            Text(text = "Edit Profile",
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = poppins,
                )

        }

        name?.let { CircularImage(it) }

        name?.let { NameColumn(it) }

        EmailColumn(email)

        PhoneColumn(phoneNumber)

        RoomColumn(room)

    }

}

@Composable
fun CircularImage(namee: String) {


     val name = generateAbbreviation(namee)

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

            Card(modifier = Modifier.size(100.dp) ,
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color(0xFF383841))) {

                Column(modifier = Modifier.fillMaxSize(),
                    Arrangement.Center,
                    Alignment.CenterHorizontally) {
                    Text(text = name,
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = poppins,
                    )
                }


            }

        }

}

fun generateAbbreviation(name: String): String {
    val words = name.split(" ")

    if (words.isEmpty() || name.isEmpty())
        return "NA"
    else if (words.size == 1) {
        return words[0].substring(0, 2).uppercase(Locale.getDefault())
    } else {
        return words[0].substring(0, 1).uppercase(Locale.getDefault()) + words[words.size-1].substring(0, 1)
            .uppercase(Locale.getDefault())
    }
}

@Composable
fun RoomColumn(room: String?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(
            text = stringResource(R.string.room_number) ,
            color = Color.White ,
            fontSize = 20.sp ,
            fontFamily = poppins ,
        )
//        Spacer(modifier = Modifier.padding(5.dp))

        room?.let {
            EditProfileItemEditScreen(mainText = it)
            {

            }
        }
    }

}

@Composable
fun EditProfileItemEditScreen(mainText: String , content: @Composable () -> Unit) {

    val tfViewModel: truefalseViewModel = hiltViewModel()
    Card(
        colors = CardDefaults.cardColors(Color(0xFF46464b)),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp , horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {


                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = mainText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFfffffe),
                        fontFamily = poppins
                    )
                }
            }

            IconButton(onClick = {
                                tfViewModel.updateShow(true)
            }, modifier = Modifier
                .size(20.dp)
                .padding(3.dp)) {
                Icon(
                    Icons.Outlined.Create, contentDescription = "Username",
                    tint = Color(0xFFA7A7A7))
            }

        }
    }
}

@Composable
fun PhoneColumn(phoneNumber: String?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(
            text = stringResource(R.string.phone_number) ,
            color = Color.White ,
            fontSize = 20.sp ,
            fontFamily = poppins ,
        )


        phoneNumber?.let {
            EditProfileItemEditScreen(mainText = it){}
        }
    }

}


@Composable
fun NameColumn(name:String) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(
            text = stringResource(R.string.name) ,
            color = Color.White ,
            fontSize = 20.sp ,
            fontFamily = poppins ,
        )
//        Spacer(modifier = Modifier.padding(5.dp))


        EditProfileItemMainScreen(mainText = name){}
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileItemMainScreen(mainText: String, onClick: () -> Unit) {

    Card(onClick = {},
        colors = CardDefaults.cardColors(Color(0xFF46464b)),
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp , horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {


                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = mainText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFfffffe),
                        fontFamily = poppins
                    )
                }
            }
        }
    }
}


@Composable
fun EmailColumn(email: String?) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = 20.dp , start = 20.dp , end = 20.dp)
    ) {

        Text(
            text = stringResource(R.string.email) ,
            color = Color.White ,
            fontSize = 20.sp ,
            fontFamily = poppins ,
        )

        email?.let {
            EditProfileItemMainScreen(mainText = it){}
        }
    }

}