package com.original.sense.psit.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.original.sense.psit.PersonModel
import com.original.sense.psit.R
import com.original.sense.psit.ui.theme.poppins


private val studentList = mutableListOf<PersonModel>()

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListDemo() {

    studentList.add(PersonModel("Anish Singh",2101641530027))
    studentList.add(PersonModel("Arnav Asthana",2101641530040))
    studentList.add(PersonModel("Arin Paliwal",2101641530038))
    studentList.add(PersonModel("Ashutosh Pandey",2101641530046))
    studentList.add(PersonModel("Sanat Kumar Mishra",2101640100231))
    studentList.add(PersonModel("Ayush Agnihotri",2101641530049))
    studentList.add(PersonModel("Satvik Shukla",2101640100235))
    studentList.add(PersonModel("Rishab Didwania",2101641530046))
    studentList.add(PersonModel("Aditya Tripathi",2101641530016))
    studentList.add(PersonModel("Devansh Tiwari",2101641530058))
    studentList.add(PersonModel("Devesh Shukla",210164010060))
    studentList.add(PersonModel("Archit Pandey",2101641530037))
    studentList.add(PersonModel("Deepak Yadav",2101640100301))
    studentList.add(PersonModel("James Malhotra",2101641530078))
    studentList.add(PersonModel("Chadwick Khan",2101640100076))

    studentList.sortWith(Comparator { a, b -> a.name.compareTo(b.name) })



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight() ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally ,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            items(studentList) { model ->
                ListItem(model = model)
            }
        }
        
        Spacer(modifier = Modifier.padding(horizontal = 50.dp))
    }
}







@Composable
fun AddScreen(navController: NavController) {
    Box (modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())
        ){

        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())) {

            DelegationAndSuspension()

            DelegationAndSuspensionButton()

            SubjectOfDelegation()

            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "Allot Lectures",
                fontFamily = poppins,
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium)
            AllottedLectures()
            DescriptionDelegation()

            CreateButton()
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DelegationAndSuspension() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight() ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = "Delegation",
            fontFamily = poppins,
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold)



        var selected by remember { mutableStateOf(false) }
        FilterChip(
            onClick = {  },
            colors = FilterChipDefaults.filterChipColors(
                containerColor = Color(0xFF1d1e23),
                labelColor = Color.White,
                selectedContainerColor = Color.Cyan,
                selectedLabelColor = Color.Black,
                selectedLeadingIconColor = Color.Black
            ),
            label = { Text("DESN162606") },
            selected = selected,
            shape = RoundedCornerShape(25.dp)
        )

    }
}


@Composable
fun DelegationAndSuspensionButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .wrapContentHeight() ,
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Green)
        ) {

        }
    }
}

@Composable
fun DescriptionDelegation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .wrapContentHeight() ,
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically
    ) {



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
                onClick = { selected1 = !selected1 },
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
                onClick = { selected2 = !selected2 },
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
                onClick = { selected3 = !selected3 },
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
                onClick = { selected4 = !selected4 },
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
                onClick = { selected5 = !selected5 },
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
                onClick = { selected6 = !selected6 },
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
                onClick = { selected7 = !selected7 },
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
                onClick = { selected8 = !selected8 },
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
            onValueChange = { text = it } ,
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




@Composable
fun ListItem(model: PersonModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        var checkedState by remember { mutableStateOf(false) }
        val paddingModifier = Modifier.padding(10.dp)
        Card(elevation = CardDefaults.cardElevation(5.dp), modifier = paddingModifier,
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(Color(0xFF383841))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                  ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checkedState , onCheckedChange = { checkedState = !checkedState },
                    colors = CheckboxDefaults.colors(checkmarkColor = Color.White,
                       checkedColor = Color.Red,
                        uncheckedColor = Color.White) )

                Column (modifier = Modifier  .padding(vertical = 10.dp)){
                    Text(
                        text = model.name ,
                        fontSize = 18.sp ,
                        fontWeight = FontWeight.SemiBold ,
                        color = Color.White ,
                        fontFamily = poppins
                    )
                    Text(
                        text = model.rollNum.toString(),
                        fontSize = 14.sp ,
                        fontWeight = FontWeight.Light ,
                        color = Color.White ,
                        fontFamily = poppins
                    )

                }
            }
        }
    }
}

