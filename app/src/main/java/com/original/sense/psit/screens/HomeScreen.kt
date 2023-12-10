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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import com.original.sense.psit.MainActivity
import com.original.sense.psit.R
import com.original.sense.psit.ViewModels.StudentListViewModel
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.composable.ListDemo
import com.original.sense.psit.composable.ReadyToTap
import com.original.sense.psit.model.PersonModel
import com.original.sense.psit.ui.theme.poppins
import org.json.JSONObject
import java.io.IOException


val info = arrayOf("2101641530046" ,"2101641530047","2101641530048","2101641530049")
val sdtList : ArrayList<String> = ArrayList()
val rollArray : ArrayList<Int> = ArrayList()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,activity: Activity ) {
    val studentListViewModel: StudentListViewModel = viewModel()
    val context = LocalContext.current.applicationContext
    var dialogVisible by remember { mutableStateOf(false) }
    val nfcAdapter by remember { mutableStateOf(NfcAdapter.getDefaultAdapter(context)) }



    val selectedItems = remember { mutableStateListOf<PersonModel>() }

    var show by remember { mutableStateOf(false) }

    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()

    val accessToken by tokenStoreViewModel.readAccess.collectAsState()

    val refreshToken by tokenStoreViewModel.readRefresh.collectAsState()

    Toast.makeText(context,"$accessToken",Toast.LENGTH_SHORT).show()
    Toast.makeText(context,"$refreshToken",Toast.LENGTH_SHORT).show()


    // Function to delete selected items
    val deleteSelectedItems: () -> Unit = {
//        studentList.removeAll(selectedItems)
        studentListViewModel.removeStudent(selectedItems)
        selectedItems.clear()
    }



    val jsonData = context.applicationContext.resources.openRawResource(
        context.applicationContext.resources.getIdentifier(
            "dataa",
            "raw",
            context.applicationContext.packageName
        )
    ).bufferedReader().use { it.readText() }


    val outputJsonString = JSONObject(jsonData)




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
                ReadyToTap()
        }

        DisposableEffect(Unit) {
            enableNfcForegroundDispatch(context,activity)

            onDispose {
                disableNfcForegroundDispatch(context,activity)
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
                    // Add the entered search text as a new item to the student list
                    val newPersonModel = PersonModel(name = "", rollNum = searchText.toLong())
                    studentListViewModel.addStudent(newPersonModel)
                })

            Row {

                Box(modifier = Modifier.size(50.dp)) {

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

        ListDemo(selectedItems = selectedItems, studentList = studentListViewModel.studentList)
    }


//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "$outputJsonString" ,
//            fontFamily = poppins ,
//            fontSize = 7.sp ,
//            color = Color.White,
//            textAlign = TextAlign.Center
//        )
//
//    }


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
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // Set the FLAG_ACTIVITY_NEW_TASK flag
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

    val intent = activity.intent
    val action = intent.action
    if (NfcAdapter.ACTION_TECH_DISCOVERED == action || NfcAdapter.ACTION_TAG_DISCOVERED == action) {
       handleTechTag(intent,context)
    }


}



 fun handleTechTag(intent: Intent , context: Context) {
    val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
    val nfca = NfcA.get(tag)
    if (nfca != null) {
        try {
            nfca.connect()

            Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show()

            // Read NFC-A tag data
            val tagData = nfca.tag.id
            if (tagData != null && tagData.size > 0) {
                val tagId: String = byteArrayToHexString(tagData)
                Toast.makeText(context,"$tagId",Toast.LENGTH_SHORT).show()
                Toast.makeText(context,"$tagData",Toast.LENGTH_SHORT).show()
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
    Box (modifier = Modifier.size(50.dp)){
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
                Toast
                    .makeText(context , "Image Clicked" , Toast.LENGTH_SHORT)
                    .show()

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
            .height(50.dp)
            .clip(RoundedCornerShape(15.dp)) ,

        placeholder = {
            Text(text = placeHolder , color = Color(0xFF222228))
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
                Toast.makeText(context , "Toast Message from the text" , Toast.LENGTH_SHORT).show()
                Toast.makeText(context , "$text" , Toast.LENGTH_LONG).show()
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



