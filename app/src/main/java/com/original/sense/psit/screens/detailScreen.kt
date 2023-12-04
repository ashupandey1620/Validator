package com.original.sense.psit.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.original.sense.psit.R
import com.original.sense.psit.composable.AddStudentScreen
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.ui.theme.poppins
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val name = "Ashutosh Pandey"
@Composable
fun detailScreen(navController: NavController) {


    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())
        .verticalScroll(rememberScrollState())) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ,
            Arrangement.SpaceBetween
        ) {

            FrontLobe(modifier = Modifier)

            Row {

                Box(modifier = Modifier.size(50.dp)) {

                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
//                            .clickable {
//                                show = true
//                            }
                        ,
                                painter = painterResource(id = R.drawable.tap) ,
                        contentDescription = ""
                    )

                }

                Spacer(modifier = Modifier.padding(6.dp))
                CircularNotificationButton(navController)
            }
        }

        DateAndCalendar()

        DateLazyList()

        AllowedLectures()



    }


}

@Composable
fun DateAndCalendar() {
    val context = LocalContext.current.applicationContext
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) // 0-based index

    val simpleDateFormat = SimpleDateFormat("MMMM, YYYY", Locale.ENGLISH)
    val formattedDate = simpleDateFormat.format(calendar.time)




    Toast.makeText(context,"month ${year}",Toast.LENGTH_SHORT).show()

    Toast.makeText(context,"month ${month}",Toast.LENGTH_SHORT).show()

    var show by remember { mutableStateOf(false) }


    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = formattedDate,
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = poppins ,
            )

            Icon(tint = Color.Gray,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .size(20.dp)
                    .clickable {
                        show = !show
                    },
                painter = painterResource(id = R.drawable.ic_right_arrow)
            )

        }


        if(show){
            AddStudentScreen()
        }



    }



}


@Composable
fun DateLazyList() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        


    }

}


@Composable
fun LazyListCardRowItem(date: String , day: String) {

    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(150.dp)
        .aspectRatio(1.5f),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(20.dp)
    ){

        Column (modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
            ){

            Text(text = date,
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = poppins ,
            )

            Text(text = day,
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = poppins ,
            )

        }

    }

    
}

@Composable
fun AllowedLectures() {

}



@Composable
fun FrontLobe( modifier: Modifier) {

    Row (
        modifier
            .fillMaxWidth(0.7f)
            .height(50.dp)){

        Box(modifier = Modifier.size(50.dp)) {

            Card(modifier = Modifier.fillMaxSize() ,
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color(0xFF383841))) {


                Column(
                    modifier = Modifier.fillMaxSize() ,
                    Arrangement.Center ,
                    Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "AS" ,
                        color = Color.White ,
                        fontSize = 20.sp ,
                        fontFamily = poppins ,
                    )
                }
            }

        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
            verticalArrangement = Arrangement.SpaceBetween) {

            Text(text = name,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = poppins ,
            )

            Text(text = "2101641530046",
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = poppins ,
            )

        }


    }

}
