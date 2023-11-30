package com.original.sense.psit.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import com.original.sense.psit.R
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.composable.ReadyToTap
import com.original.sense.psit.composable.RemoveStudentScreen
import com.original.sense.psit.ui.theme.poppins


val alloted = BooleanArray(8)
var title = ""
var description = ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController) {


    var show by remember {
        mutableStateOf(false)
    }


    if (show) {
        BottomSheetDialog(
            onDismissRequest = {
                show = false
            } ,
            properties = BottomSheetDialogProperties()
        ) {
            RemoveStudentScreen()
        }
    }


    val context = LocalContext.current.applicationContext

    var unSelectedDelegation by remember { mutableStateOf(false) }
    var unSelectedSuspension by remember { mutableStateOf(true) }

    Box (modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())
        ){

        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())) {

            val name : String = if (!unSelectedDelegation)
                "Delegation"
            else
                "Suspension"
            DelegationAndSuspension(name)
            Spacer(modifier = Modifier.padding(5.dp))

            Card(shape = RoundedCornerShape(25.dp),
                colors = CardDefaults.cardColors(Color(0xFF2d2e34)),
                border = BorderStroke(0.5.dp, Color.White)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceEvenly ,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    FilterChip(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp) ,

                        onClick = {
                            if (unSelectedDelegation) {
                                unSelectedDelegation = !unSelectedDelegation
                                unSelectedSuspension = !unSelectedSuspension
                            }
                        },

                        colors = FilterChipDefaults.filterChipColors(

                            containerColor = Color(0xFFfffffe),
                            disabledContainerColor = Color(0xFFfffffe),
                            labelColor = Color.Black,
                            selectedContainerColor =  Color(0xFF2d2e34),
                            selectedLabelColor = Color.White
                        ),

                        label = {

                            Column(
                                modifier = Modifier.fillMaxSize() ,
                                horizontalAlignment = Alignment.CenterHorizontally ,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = "Delegation" ,
                                    fontSize = 20.sp , fontFamily = poppins
                                )
                            }
                        },

                        selected = unSelectedDelegation,
                        shape = RoundedCornerShape(25.dp)
                    )

                    FilterChip(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp),
                        onClick = {
                            if (unSelectedSuspension) {
                                unSelectedSuspension = !unSelectedSuspension
                                unSelectedDelegation = !unSelectedDelegation
                            }
                        },

                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color(0xFFfffffe),
                            disabledContainerColor = Color(0xFFfffffe),
                            labelColor = Color.Black,
                            selectedContainerColor = Color(0xFF2d2e34),
                            selectedLabelColor = Color.White,
                        ),

                        label = {
                            Column(
                                modifier = Modifier.fillMaxSize() ,
                                horizontalAlignment = Alignment.CenterHorizontally ,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = "Suspension" ,
                                    fontSize = 20.sp , fontFamily = poppins
                                )
                            }
                        },

                        selected = unSelectedSuspension,
                        shape = RoundedCornerShape(25.dp)
                    )



                }
            }

            if(!unSelectedDelegation) {
                Spacer(modifier = Modifier.padding(8.dp))

                SubjectOfDelegation()

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Allot Lectures" ,
                    fontFamily = poppins ,
                    fontSize = 22.sp ,
                    color = Color.White ,
                    fontWeight = FontWeight.Medium
                )
                AllottedLectures()

                Spacer(modifier = Modifier.padding(4.dp))

                DescriptionDelegation()

                Spacer(modifier = Modifier.padding(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {

                    Button(
                        onClick = {
show = !show
                                  Toast.makeText(context,title,Toast.LENGTH_LONG).show()
                                  Toast.makeText(context,alloted.contentToString(),Toast.LENGTH_LONG).show()
                                  Toast.makeText(context, description,Toast.LENGTH_LONG).show()

                        } ,
                        colors = ButtonDefaults.buttonColors(Color(0xFF3068de)) ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = "Create" , color = Color.White ,
                            fontSize = 20.sp ,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

            }else {

                //----------------------------------------------Suspension Page


                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Reason: " ,
                    fontFamily = poppins ,
                    fontSize = 22.sp ,
                    color = Color.White ,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.padding(4.dp))


                ReasonEditText()


                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Date: " ,
                    fontFamily = poppins ,
                    fontSize = 22.sp ,
                    color = Color.White ,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.padding(4.dp))

                DateStartEnd()

                Spacer(modifier = Modifier.padding(146.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),

                ) {

                    Button(
                        onClick = {
show = !show
                        } ,
                        colors = ButtonDefaults.buttonColors(Color(0xFF3068de)) ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = "Create" , color = Color.White ,
                            fontSize = 20.sp ,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }


            }

        }

    }
}

@Composable
fun DateStartEnd() {

    val keyboardController = LocalSoftwareKeyboardController.current
    var text1 by rememberSaveable { mutableStateOf("") }
    var text2 by rememberSaveable { mutableStateOf("") }

    val containerColor = Color(0xFF383838)

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        Box(modifier = Modifier
            .weight(1f)
            .padding(horizontal = 3.dp)) {


            OutlinedTextField(
                value = text1 ,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.desc) ,
                        contentDescription = "Start Date" ,
                        tint = Color(0xFFA7A7A7)
                    )
                } ,
                onValueChange = { text1 = it } ,
                shape = RoundedCornerShape(30.dp) ,

                placeholder = {
                    Text(
                        text = "Start Date" ,
                        color = Color(0xFFA7A7A7) ,
                        fontSize = 16.sp
                    )
                } ,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next ,
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
                singleLine = true ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(top = 5.dp) ,
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()

                    }
                )

            )
        }

        Box (modifier = Modifier
            .weight(1f)
            .padding(horizontal = 3.dp)) {


            OutlinedTextField(
                value = text2 ,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.desc) ,
                        contentDescription = "End Date" ,
                        tint = Color(0xFFA7A7A7)
                    )
                } ,
                onValueChange = { text2 = it } ,
                shape = RoundedCornerShape(30.dp) ,

                placeholder = {
                    Text(
                        text = "End Date" ,
                        color = Color(0xFFA7A7A7) ,
                        fontSize = 16.sp
                    )
                } ,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next ,
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
                singleLine = true ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(top = 5.dp) ,
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()

                    }
                )

            )
        }



    }

}

@Composable
fun ReasonEditText() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    val containerColor = Color(0xFF383838)
    OutlinedTextField(
        value = text ,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.desc) ,
                contentDescription = "Reason" ,
                tint = Color(0xFFA7A7A7)
            )
        } ,
        onValueChange = { text = it } ,
        shape = RoundedCornerShape(30.dp) ,

        placeholder = { Text(text = "Description" , color = Color(0xFFA7A7A7) , fontSize = 16.sp) } ,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next ,
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
        singleLine = true ,
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(top = 5.dp) ,
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()

            }
        )

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DelegationAndSuspension(name: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = name,
            fontFamily = poppins,
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold)

    }
}



@Composable
fun DescriptionDelegation() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {



        Text(text = "Description",
            fontFamily = poppins,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )

        Spacer(modifier = Modifier.padding(2.dp))

        val keyboardController = LocalSoftwareKeyboardController.current
        var text by rememberSaveable { mutableStateOf("") }

        val containerColor = Color(0xFF383838)
        OutlinedTextField(
            value = text ,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.desc) ,
                    contentDescription = "description" ,
                    tint = Color(0xFFA7A7A7)
                )
            } ,
            onValueChange = { text = it
                            description = text} ,
            shape = RoundedCornerShape(30.dp) ,

            placeholder = { Text(text = "Description" , color = Color(0xFFA7A7A7) , fontSize = 16.sp) } ,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next ,
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
            singleLine = true ,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(top = 5.dp) ,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()

                }
            )

        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllottedLectures() {

    var selected1 by remember { mutableStateOf(false) }
    var selected2 by remember { mutableStateOf(false) }
    var selected3 by remember { mutableStateOf(false) }
    var selected4 by remember { mutableStateOf(false) }
    var selected5 by remember { mutableStateOf(false) }
    var selected6 by remember { mutableStateOf(false) }
    var selected7 by remember { mutableStateOf(false) }
    var selected8 by remember { mutableStateOf(false) }


    Column(modifier = Modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 1.dp)
                .wrapContentHeight() ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            FilterChip(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onClick = { selected1 = !selected1
                          alloted[0] = !alloted[0]
                          },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFF1d1e23),
                    labelColor = Color.White,
                    selectedContainerColor = Color.Green,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.Black
                ),

                label = {

                    Column(
                        modifier = Modifier.fillMaxSize() ,
                        horizontalAlignment = Alignment.CenterHorizontally ,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = "Lecture 1" ,
                            fontSize = 20.sp , fontFamily = poppins
                        )
                    }
                },

                selected = selected1,
                shape = RoundedCornerShape(25.dp)
            )

            FilterChip(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onClick = { selected2 = !selected2
                            alloted[1] = !alloted[1]
                          },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFF1d1e23),
                    labelColor = Color.White,
                    selectedContainerColor = Color.Green,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.Black
                ),

                label = {

                    Column(
                        modifier = Modifier.fillMaxSize() ,
                        horizontalAlignment = Alignment.CenterHorizontally ,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = "Lecture 2" ,
                            fontSize = 20.sp , fontFamily = poppins
                        )
                    }
                },

                selected = selected2,
                shape = RoundedCornerShape(25.dp)
            )


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 1.dp)
                .wrapContentHeight() ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            FilterChip(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onClick = { selected3 = !selected3
                            alloted[2] = !alloted[2]
                          },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFF1d1e23),
                    labelColor = Color.White,
                    selectedContainerColor = Color.Green,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.Black
                ),

                label = {

                    Column(
                        modifier = Modifier.fillMaxSize() ,
                        horizontalAlignment = Alignment.CenterHorizontally ,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = "Lecture 3" ,
                            fontSize = 20.sp , fontFamily = poppins
                        )
                    }
                },

                selected = selected3,
                shape = RoundedCornerShape(25.dp)
            )

            FilterChip(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onClick = { selected4 = !selected4
                            alloted[3] = !alloted[3]
                          },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFF1d1e23),
                    labelColor = Color.White,
                    selectedContainerColor = Color.Green,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.Black
                ),

                label = {

                    Column(
                        modifier = Modifier.fillMaxSize() ,
                        horizontalAlignment = Alignment.CenterHorizontally ,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = "Lecture 4" ,
                            fontSize = 20.sp , fontFamily = poppins
                        )
                    }
                },

                selected = selected4,
                shape = RoundedCornerShape(25.dp)
            )


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 1.dp)
                .wrapContentHeight() ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            FilterChip(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onClick = { selected5 = !selected5
                            alloted[4] = !alloted[4]
                          },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFF1d1e23),
                    labelColor = Color.White,
                    selectedContainerColor = Color.Green,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.Black
                ),

                label = {

                    Column(
                        modifier = Modifier.fillMaxSize() ,
                        horizontalAlignment = Alignment.CenterHorizontally ,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = "Lecture 5" ,
                            fontSize = 20.sp , fontFamily = poppins
                        )
                    }
                },

                selected = selected5,
                shape = RoundedCornerShape(25.dp)
            )

            FilterChip(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onClick = { selected6 = !selected6
                            alloted[5] = !alloted[5]
                          },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFF1d1e23),
                    labelColor = Color.White,
                    selectedContainerColor = Color.Green,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.Black
                ),
                label = {

                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Text(  modifier = Modifier.padding(vertical = 4.dp),

                            text="Lecture 6" ,
                            fontSize = 20.sp ,
                            fontFamily = poppins
                        )
                    }},
                selected = selected6,
                shape = RoundedCornerShape(25.dp)
            )


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 1.dp)
                .wrapContentHeight() ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            FilterChip(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onClick = { selected7 = !selected7
                            alloted[6] = !alloted[6]
                          },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFF1d1e23),
                    labelColor = Color.White,
                    selectedContainerColor = Color.Green,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.Black
                ),
                label = {

                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center) {
                        Text(  modifier = Modifier.padding(vertical = 4.dp),
                            text="Lecture 7" ,
                            fontSize = 20.sp ,
                            fontFamily = poppins
                        )
                    }},
                selected = selected7,
                shape = RoundedCornerShape(25.dp)
            )
            FilterChip(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp),
                onClick = { selected8 = !selected8
                            alloted[7] = !alloted[7]
                          },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0xFF1d1e23),
                    labelColor = Color.White,
                    selectedContainerColor = Color.Green,
                    selectedLabelColor = Color.White,
                    selectedLeadingIconColor = Color.Black
                ),
                label = {
                                    Column(modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center) {
                                    Text(  modifier = Modifier.padding(vertical = 4.dp),
                                        text="Lecture 8" ,
                                        fontSize = 20.sp ,
                                        fontFamily = poppins
                                    )
                            }
                        },
                selected = selected8,
                shape = RoundedCornerShape(25.dp)
            )


        }
    }
}

@Composable
fun SubjectOfDelegation() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {



        Text(text = "Subject Of Delegation:",
            fontFamily = poppins,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
            )

        Spacer(modifier = Modifier.padding(4.dp))

        val keyboardController = LocalSoftwareKeyboardController.current
        var text by rememberSaveable { mutableStateOf("") }

        val containerColor = Color(0xFF383838)
        OutlinedTextField(
            value = text ,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.titledelegation) ,
                    contentDescription = "Title" ,
                    tint = Color(0xFFA7A7A7)
                )
            } ,
            onValueChange = { text = it
                            title = text} ,
            shape = RoundedCornerShape(30.dp) ,

            placeholder = { Text(text = "Title" , color = Color(0xFFA7A7A7) , fontSize = 16.sp) } ,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next ,
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
            singleLine = true ,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(top = 5.dp) ,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()



                }
            )

        )
    }
}

@Composable
fun CreateButton() {
    Column(modifier = Modifier
        .size(200.dp)
        .background(Color.Gray)) {



    }
}



//val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
//
//if (nfcAdapter == null || !nfcAdapter.isEnabled) {
//    // NFC is not supported or disabled, show alert
//    val builder = AlertDialog.Builder(this)
//    builder.setTitle("NFC Scanner")
//    builder.setMessage("Please enable NFC scanner to use this feature.")
//    builder.setPositiveButton("Open Settings") { dialog, which ->
//        // Open NFC settings
//        val intent = Intent(Settings.ACTION_NFC_SETTINGS)
//        ContextCompat.startActivity(intent)
//    }
//    builder.setNegativeButton("Cancel", null)
//    builder.create().show()
//}
//
