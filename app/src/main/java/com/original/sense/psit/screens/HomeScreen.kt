package com.original.sense.psit.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.original.sense.psit.R
import com.original.sense.psit.ui.theme.poppins

@Composable
fun HomeScreen(navController: NavController) {


    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())){


        val textState = remember {
            mutableStateOf(TextFieldValue(""))
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            Arrangement.SpaceBetween) {

            SearchView(state = textState ,
                placeHolder = "Search" ,
                modifier = Modifier)
            
            Row {

                CircularTapButton()
                Spacer(modifier = Modifier.padding(6.dp))
                CircularNotificationButton()
            }
        }

        Row (  modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp , end = 20.dp),
            Arrangement.SpaceBetween){
            
            Text(text = "Student List:",
                color = Color.White,
                fontSize = 27.sp,
                fontFamily = poppins
                )

            Icon(modifier = Modifier.size(35.dp),
            painter = painterResource(id = R.drawable.delete)
            , contentDescription ="delete Icon" )
        }





    }



}



@Composable
fun CircularNotificationButton() {

    Box (modifier = Modifier.size(50.dp)){
            Image(modifier = Modifier
                .clip(CircleShape)
                .fillMaxSize(),
                painter = painterResource(id = R.drawable.notifybutton) ,
                contentDescription = "" )

    }
}


@Composable
fun CircularTapButton() {

    Box (modifier = Modifier.size(50.dp)){

        Image(modifier = Modifier
            .clip(CircleShape)
            .fillMaxSize(),
            painter = painterResource(id = R.drawable.tap) ,
            contentDescription = "" )

    }
}

@Composable
fun GradientBackground(): Brush {

    val colors = listOf(
        Color(0xFF383838),
        Color(0xFF222228),
        Color(0xFF222228),
        Color(0xFF222228),
        Color(0xFF222228))

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = Offset(Float.POSITIVE_INFINITY,
            Float.POSITIVE_INFINITY)
    )
}

@Composable
fun ColumnItem(item: String) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = item, modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 22.sp)
        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    state: MutableState<TextFieldValue> ,
    placeHolder: String ,
    modifier: Modifier
) {


        TextField(

            value = state.value ,
            onValueChange = { value ->
                state.value = value
            } ,
            modifier
                .fillMaxWidth(0.6f)
                .height(50.dp)
                .clip(RoundedCornerShape(15.dp))
                ,
            placeholder = {
                Text(text = placeHolder,fontSize = 15.sp, color =  Color(0xFF222228))
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

        )

}
