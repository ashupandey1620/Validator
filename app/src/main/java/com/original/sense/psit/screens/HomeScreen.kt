package com.original.sense.psit.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.nfc.NfcAdapter
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import com.original.sense.psit.R
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.composable.NFCAlertDialog
import com.original.sense.psit.composable.ReadyToTap
import com.original.sense.psit.ui.theme.poppins
import org.json.JSONObject


val info = arrayOf("2101641530046" ,"2101641530047","2101641530048","2101641530049")
val sdtList : ArrayList<String> = ArrayList()
val rollArray : ArrayList<Integer> = ArrayList()


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current.applicationContext

    val jsonData = context.applicationContext.resources.openRawResource(
        context.applicationContext.resources.getIdentifier(
            "dataa",
            "raw",
            context.applicationContext.packageName
        )
    ).bufferedReader().use { it.readText() }


    val outputJsonString = JSONObject(jsonData)

//    val userarray = outputJsonString.getJSONArray("users") as JsonArray



//    for (i in 0 until userarray.size()){
//        rollArray.add()
//    }


    val nfcAdapter by remember { mutableStateOf(NfcAdapter.getDefaultAdapter(context)) }
    val showDialog = remember { mutableStateOf(false) }
    var a = false

    if (nfcAdapter == null || !nfcAdapter.isEnabled) {
        a = true
        showDialog.value = true

    }

    Toast.makeText(context,"$a",Toast.LENGTH_LONG).show()

    if(a) {
       NFCAlertDialog(context = context)
    }





    var show by remember {
        mutableStateOf(false)
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
                ReadyToTap()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = GradientBackground())
    ) {


        val textState = remember {
            mutableStateOf(TextFieldValue(""))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ,
            Arrangement.SpaceBetween
        ) {

            SearchView(
                placeHolder = "Search" ,
                modifier = Modifier)

            Row {

                Box(modifier = Modifier.size(50.dp)) {

                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
                            .clickable {
                                show = !show
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
               // navController.navigate("studentProfileInfo")
                navController.navigate("detailedScreen")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete) ,
                    contentDescription = "delete Icon" ,
                    tint = Color.White
                )
            }
        }



       // ListDemo(sdtList)

    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$outputJsonString" ,
            fontFamily = poppins ,
            fontSize = 7.sp ,
            color = Color.White,
            textAlign = TextAlign.Center
        )


//      "  Click on \n\n" +
//        "“Tap Button” to\n\n Add a Student"
    }
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
    modifier: Modifier
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
            .clip(RoundedCornerShape(15.dp)),

            placeholder = {
                Text(text = placeHolder, color =  Color(0xFF222228))
            } ,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ) ,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Search , contentDescription = "Search", tint =    Color(0xFF222228))
            },
            maxLines = 1 ,
            singleLine = true ,
            textStyle = TextStyle(
                color =  Color(0xFF222228) , fontSize = 15.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ) ,
        keyboardActions = KeyboardActions(
            onDone = {

                keyboardController?.hide()
                Toast.makeText(context,"Toast Message from the text",Toast.LENGTH_SHORT).show()


                Toast.makeText(context,"$text",Toast.LENGTH_LONG).show()


                if(info.contains(text))
                {
                    sdtList.add(text)

                }


            }
        )

        )

}




