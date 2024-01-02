package com.original.sense.psit.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.NfcA
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import com.original.sense.psit.API.PsitApi
import com.original.sense.psit.MainActivity
import com.original.sense.psit.R
import com.original.sense.psit.ViewModels.HomeScreenViewModel
import com.original.sense.psit.ViewModels.PsitViewModel
import com.original.sense.psit.ViewModels.StudentListViewModel
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.composable.ListDemo
import com.original.sense.psit.composable.ReadyToTap
import com.original.sense.psit.model.PersonModel
import com.original.sense.psit.model.PostModel.GetPwdPost
import com.original.sense.psit.ui.theme.poppins
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,activity: Activity ,studentListViewModel: StudentListViewModel) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color(0xFF222228)
    systemUiController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = false
    )

    val showToast = remember { mutableStateOf(false) }
    val toastMessage = remember { mutableStateOf("") }


    val context = LocalContext.current.applicationContext

    var dialogVisible by remember { mutableStateOf(false) }

    val nfcAdapter by remember { mutableStateOf(NfcAdapter.getDefaultAdapter(context)) }

    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()

    val psitViewModel : PsitViewModel = hiltViewModel()

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    val accessToken by tokenStoreViewModel.readAccess.collectAsState()

    val responseProfileData by psitViewModel.getUserProfileData.observeAsState()

    LaunchedEffect(Unit){
        psitViewModel.getUserProfileData(accessToken.toString())
    }

//    LaunchedEffect(responseProfileData) {
//        responseProfileData?.let { response ->
//            showToast.value = true
//            toastMessage.value = " ${response.responseData.name.toString()}"
//            Log.d("okhttp","$response")
//        }
//    }
//
//    if (showToast.value) {
//        Toast.makeText(context, toastMessage.value, Toast.LENGTH_SHORT).show()
//        showToast.value = false // Reset toast state
//    }




    accessToken?.let { str ->
        access = accessToken.toString()
    }

    val selectedItems = remember { mutableStateListOf<PersonModel>() }

    var show by remember { mutableStateOf(false) }

    //Lambda Functions
    val deleteSelectedItems: () -> Unit = {
        studentListViewModel.removeStudent(selectedItems)
        selectedItems.clear()
    }

    var nfcEnabled by remember { mutableStateOf(false) }

    if (show) {
        BottomSheetDialog(
            onDismissRequest = {
                show = false
            },
            properties = BottomSheetDialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                dismissWithAnimation = false
            )
        ){
            ReadyToTap(context,activity)
        }

        DisposableEffect(Unit) {
                enableNfcForegroundDispatch(context, activity)
            onDispose {
                disableNfcForegroundDispatch(context, activity)
            }
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBackground())
    ) {


        val textState = remember { mutableStateOf(TextFieldValue("")) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ,
            Arrangement.SpaceBetween
        ) {

            SearchView(
                placeHolder = "Search" ,
                modifier = Modifier,
                onSearchSubmit = { searchText ->
                    studentListViewModel.addStudent(searchText.toLong())
                })

            Row {

                Box(modifier = Modifier.size(55.dp)) {

                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
                            .clickable {

                                if (nfcAdapter == null || !nfcAdapter.isEnabled) {
                                    dialogVisible = true
                                } else {
                                    show = !show
                                }


                            } ,
                        painter = painterResource(id = R.drawable.tap) ,
                        contentDescription = ""
                    )

                }

                Spacer(modifier = Modifier.padding(6.dp))
                CircularNotificationButton(navController)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp , end = 20.dp) ,
            Arrangement.SpaceBetween
        ) {

            Text(
                text = "Student List:" ,
                color = Color.White ,
                fontSize = 27.sp ,
                fontFamily = poppins
            )

            if(!studentListViewModel.studentList.isEmpty()) {
                IconButton(modifier = Modifier.size(35.dp) , onClick = {
                    deleteSelectedItems()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete) ,
                        contentDescription = "delete Icon" ,
                        tint = Color.White
                    )
                }
            }
        }

        ListDemo(selectedItems = selectedItems,
            studentList = studentListViewModel.studentList,
            navController)
    }


    if(dialogVisible) {
        AlertDialog(
            onDismissRequest = { dialogVisible = false } ,
            title = { Text("NFC Scanner") } ,
            text = { Text("Please enable NFC scanner to use this feature.") } ,
            confirmButton = {
                Button(
                    onClick = {
                        // Open NFC settings
                        dialogVisible = false
                        val intent = Intent(Settings.ACTION_NFC_SETTINGS)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(intent)
                    }
                ) {
                    Text("Open Settings")
                }
            } ,
            dismissButton = {
                Button(
                    onClick = {
                        dialogVisible = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )

    }

}


  @OptIn(DelicateCoroutinesApi::class)
  fun handleTechTag(intent: Intent , context: Context,callback: (String) -> Unit) {
    val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
    val nfca = NfcA.get(tag)

    if (nfca != null) {
        try {
            nfca.connect()

           // Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show()

            // Read NFC-A tag data
            val tagData = nfca.tag.id
            if (tagData != null && tagData.size > 0) {
                var tagId: String = byteArrayToHexString(tagData)
                tagId = tagId.uppercase()
            //    Toast.makeText(context, tagId ,Toast.LENGTH_SHORT).show()
              //  Toast.makeText(context,"$tagData",Toast.LENGTH_SHORT).show()


                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                val client: OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor).
                    build()

                    val retrofit: Retrofit = Retrofit.Builder()
                        .baseUrl("http://18.61.72.79/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()

                val apiService: PsitApi = retrofit.create(PsitApi::class.java)
                val tagIdModel = GetPwdPost(tagId)


                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        val response = apiService.getChipPwd("Bearer $access",tagIdModel)
                        if (response.body()?.errors == true) {
                          //  Log.d("KodanKing","${response.body()}")
                        } else {
                          //  Log.d("KodanKing - No error","${response.body()}")
                            val password = response.body()?.responseData?.password

                            val a = password?.substring(0 , 2)!!.toInt(16).toByte()
                            val b = password.substring(2 , 4).toInt(16).toByte()
                            val c = password.substring(4 , 6).toInt(16).toByte()
                            val d = password.substring(6 , 8).toInt(16).toByte()
                            val pwd = byteArrayOf(a , b , c , d)
                            val authCommand = byteArrayOf(
                                0x1B.toByte() ,  // PWD_AUTH command
                                pwd[0] , pwd[1] , pwd[2] , pwd[3]
                            )

                            try {
                                nfca.connect()
                                //  Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                                val result: ByteArray = nfca.transceive(authCommand)
                                if (result != null && result.size >= 2 && result[0] == 0x00.toByte() && result[1] == 0x00.toByte()) {
                                    // Authentication successful
                                    val PUIDData: ByteArray = nfca.transceive(byteArrayOf(0x30.toByte() , 0x04.toByte()))
                                    var studentId: String = byteArrayToHexString(PUIDData)
                                    //Toast.makeText(MainActivity.this, "NFC-A Tag Value: " + PUIDValue, Toast.LENGTH_SHORT).show();
                                    studentId = studentId.substring(0 , 26)
                                   // Log.d("KodanKing student id", studentId)
                                    studentId = getOddIndexedCharacters(studentId)
                                    callback(studentId)
                                    //Log.d("KodanKing Student id final", studentId)
                                } else {
                                   // Toast.makeText(context , "Tag Data is null" , Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                                // Toast.makeText(MainActivity.this, "Error transceiving data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (e: Exception) {
                        Log.d("KodanKing - Exception","$e")
                    }
                }



            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context , "Error reading NFC-A tag" , Toast.LENGTH_SHORT).show()
        } finally {
            try {
                nfca.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

fun getOddIndexedCharacters(input: String): String {
    val result = StringBuilder()
    for (i in input.indices) {
        if (i % 2 != 0) {
            result.append(input[i])
        }
    }
    return result.toString()
}

fun enableNfcForegroundDispatch(context: Context,activity: Activity) {
    val intent = Intent(context, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
    nfcAdapter?.enableForegroundDispatch(
        activity,
        PendingIntent.getActivity(
            context,
            0,
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_MUTABLE
        ),
        null,
        null
    )
}

 fun disableNfcForegroundDispatch(context: Context,activity: Activity) {
    val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
    nfcAdapter?.disableForegroundDispatch(activity)
}



@Composable
fun CircularNotificationButton(navController: NavController) {
    val context = LocalContext.current.applicationContext
    Box (modifier = Modifier.size(55.dp)){
            Image(modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize()
                .clickable {
                    navController.navigate("notification")
                },
                painter = painterResource(id = R.drawable.notifybutton) ,
                contentDescription = "" )

    }
}


@Composable
fun CircularTapButton() {
    val context = LocalContext.current.applicationContext
    Box (modifier = Modifier.size(50.dp)){

        Image(modifier = Modifier
            .clip(CircleShape)
            .fillMaxSize()
            .clickable {


            },
            painter = painterResource(id = R.drawable.tap) ,
            contentDescription = "" )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    placeHolder: String ,
    modifier: Modifier,
    onSearchSubmit: (String) -> Unit
) {

     val context = LocalContext.current.applicationContext

    var text by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text ,
        onValueChange = {
            text = it
        } ,
        modifier
            .fillMaxWidth(0.6f)
            .height(57.dp)
            .clip(RoundedCornerShape(15.dp)),
        placeholder = {
            Row {
                Text(text = placeHolder, color = Color(0xFF222228),fontSize = 15.sp)
            }
        } ,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White ,
            unfocusedContainerColor = Color.White ,
            disabledContainerColor = Color.White ,
        ) ,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search ,
                contentDescription = "Search" ,
                tint = Color(0xFF222228)
            )
        } ,
        maxLines = 1 ,
        singleLine = true ,
        textStyle = TextStyle(
            color = Color(0xFF222228) , fontSize = 15.sp
        ) ,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ) ,
        keyboardActions = KeyboardActions(
            onDone = {

                keyboardController?.hide()
            //    Toast.makeText(context , "Toast Message from the text" , Toast.LENGTH_SHORT).show()
            //    Toast.makeText(context , "$text" , Toast.LENGTH_LONG).show()
                onSearchSubmit(text)

            }
        )

    )

}

private fun byteArrayToHexString(array: ByteArray): String {
    val stringBuilder = StringBuilder()
    for (byte in array) {
        stringBuilder.append(String.format("%02x", byte))
    }
    return stringBuilder.toString()
}



