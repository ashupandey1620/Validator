package com.original.sense.psit.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.original.sense.psit.R
import com.original.sense.psit.ViewModels.DateItem
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.ViewModels.DetailScreenViewModel
import com.original.sense.psit.composable.GradientBackground
import com.original.sense.psit.model.AssignedLecture
import com.original.sense.psit.model.AssignedLectureModel
import com.original.sense.psit.model.PostModel.PermissionPost
import com.original.sense.psit.ui.theme.poppins
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale


val assignedList = mutableListOf<AssignedLectureModel>().apply {
    add(AssignedLectureModel("8JEI9JDOESE0WR", listOf(1,2,3,4) ,"Raghav Tiwari"))
    add(AssignedLectureModel("9DDJFNEIFDKDDJ", listOf(5,7,8) ,"Ashutosh Pandey"))
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun detailScreen(navController: NavController , rollNum: Long? , name: String?) {

    val context = LocalContext.current.applicationContext
    val showToast = remember { mutableStateOf(false) }
    val toastMessage = remember { mutableStateOf("") }
    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()
    val accessToken by tokenStoreViewModel.readAccess.collectAsState()



    val detailScreenViewModel: DetailScreenViewModel = hiltViewModel()



    val responsePermission by detailScreenViewModel.getPermission.observeAsState()

    LaunchedEffect(Unit){

        val studentPost = rollNum?.let { listOf(it.toLong()) }?.let { PermissionPost(it) }

        studentPost?.let { detailScreenViewModel.getPermission(accessToken.toString(), it) }
    }

    LaunchedEffect(responsePermission) {
        responsePermission?.let { response ->
            showToast.value = true
            toastMessage.value = " ${response.error}"
        }
    }

    if (showToast.value) {
        Toast.makeText(context, toastMessage.value, Toast.LENGTH_SHORT).show()
        showToast.value = false // Reset toast state
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = GradientBackground())
        .verticalScroll(rememberScrollState())
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ,
            Arrangement.SpaceBetween
        ) {

            FrontLobe(modifier = Modifier,rollNum,name,navController)

            Row {

                Box(modifier = Modifier.size(55.dp)) {

                    Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
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

        DateLazyList(detailScreenViewModel)

        AllowedLectures()

    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateAndCalendar() {
    val context = LocalContext.current.applicationContext
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) // 0-based index

    val simpleDateFormat = SimpleDateFormat("MMMM, YYYY", Locale.ENGLISH)
    val formattedDate = simpleDateFormat.format(calendar.time)



    var show by remember { mutableStateOf(false) }


    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = formattedDate ,
                color = Color.White ,
                fontSize = 24.sp ,
                fontFamily = poppins ,
            )

            Icon(
                tint = Color.Gray ,
                contentDescription = "" ,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .size(20.dp)
                    .clickable {
                        show = !show
                    } ,
                painter = painterResource(id = R.drawable.ic_right_arrow)
            )

        }



        AnimatedVisibility(
            show ,
            enter = expandVertically() ,
            exit = shrinkVertically()
        ) {

            val currentMonth = remember { YearMonth.now() }
            val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
            val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
            // val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

            val daysOfWeek = remember { daysOfWeek() }

            val state = rememberCalendarState(
                startMonth = startMonth ,
                endMonth = endMonth ,
                firstVisibleMonth = currentMonth ,
                //  firstDayOfWeek = firstDayOfWeek
                firstDayOfWeek = daysOfWeek.first() ,
                //outDateStyle = OutDateStyle.EndOfGrid
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp) ,
                colors = CardDefaults.cardColors(containerColor = Color(0xFF383841)) ,
                elevation = CardDefaults.cardElevation(10.dp) ,
                shape = RoundedCornerShape(30.dp)
            ) {
                HorizontalCalendar(
                    contentPadding = PaddingValues(8.dp) ,
                    userScrollEnabled = false ,
                    state = state ,
                    dayContent = { Day(it) } ,
                    monthHeader = {
                        DaysOfWeekTitle(daysOfWeek = daysOfWeek) // Use the title as month header
                    }

                )

            }
        }
    }

    }



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                color = Color.White
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Day(day: CalendarDay) {
    Box(
        modifier = Modifier
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = if (day.position == DayPosition.MonthDate) Color.White else Color.Gray
        )
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateLazyList(detailScreenViewModel: DetailScreenViewModel) {
    var selectedIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        LazyRow {
            itemsIndexed(detailScreenViewModel.generateNextSevenDays()) { index, item ->
                LazyListCardRowItem(
                    item,
                    isSelected = selectedIndex == index,
                    onItemClicked = {
                        selectedIndex = index
                    }
                )
            }
        }
    }
}

@Composable
fun LazyListCardRowItem(
    dateItem: DateItem,
    isSelected: Boolean,
    onItemClicked: () -> Unit
) {
    val color = if (isSelected) Color(0xFF3068de) else Color(0xFF383841)

    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(120.dp)
            .aspectRatio(0.5f)
            .clickable(onClick = onItemClicked),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(35.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = dateItem.date,
                color = Color.White,
                fontSize = 22.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = dateItem.day.substring(0, 3),
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = poppins
            )
        }
    }
}


@Composable
fun AllowedLectures() {


    val assignedListFinal = mutableListOf<AssignedLecture>()

// Loop through assignedList to duplicate entries based on the number of lectures
    assignedList.forEach { assignedModel ->
        assignedModel.lecture.forEach { lecture ->
            val newAssignedModel = AssignedLecture(
                permission_id = assignedModel.permission_id,
                lecture = lecture,
                assignedBy = assignedModel.assignedBy
            )
            assignedListFinal.add(newAssignedModel)
        }
    }

    Column(modifier = Modifier.height(600.dp)) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp) ,
            Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Allowed Lectures: " ,
                color = Color.White ,
                fontSize = 25.sp ,
                fontFamily = poppins
            )


            Icon(
                modifier = Modifier.size(25.dp) ,
                painter = painterResource(id = R.drawable.delete) ,
                contentDescription = "delete Icon" ,
                tint = Color.White
            )

        }



        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            items(assignedListFinal) { model ->
                ListItem2(model = model)
            }
        }
    }
}


@Composable
fun ListItem2(model: AssignedLecture) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        var checkedState by remember { mutableStateOf(false) }
        val paddingModifier = Modifier.padding(10.dp)
        Card(elevation = CardDefaults.cardElevation(5.dp), modifier = paddingModifier,
            shape = RoundedCornerShape(24.dp) ,
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

                Column (modifier = Modifier.padding(vertical = 10.dp)){


                    Text(
                        text = model.permission_id ,
                        fontSize = 12.sp ,
                        fontWeight = FontWeight.SemiBold ,
                        color = Color(0xFFF6F6F6) ,
                        fontFamily = poppins
                    )

                    Text(
                        text = "Lecture - ${model.lecture}",
                        fontSize = 18.sp ,
                        fontWeight = FontWeight.SemiBold ,
                        color = Color.White ,
                        fontFamily = poppins
                    )

                    Text(
                        text = "Assigned By Mr. ${model.assignedBy}",
                        fontSize = 14.sp ,
                        fontWeight = FontWeight.Light ,
                        color = Color(0xFF7CEDED) ,
                        fontFamily = poppins
                    )
                }
            }
        }
    }
}



@Composable
fun FrontLobe(modifier: Modifier , rollNum: Long? , name: String? , navController: NavController) {

    Row (
        modifier
            .fillMaxWidth(0.7f)
            .height(50.dp)
            .clickable {
                navController.navigate("studentProfileInfo"+"/${rollNum}")
            }){

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
                        text = if(name!=null)generateAbbreviation(name)
                        else
                            "Student",
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

            if (name != null) {
                Text(text = name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = poppins ,
                )
            }

            Text(text = rollNum.toString(),
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = poppins ,
            )

        }


    }

}


@Composable
fun ImageFromUrl(url: String) {
    // Create an ImagePainter using the URL
    val painter = rememberImagePainter(
        data = url,
        builder = {
            transformations(CircleCropTransformation()) 
        }
    )

    // Load the image with Image composable
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        colorFilter = ColorFilter.tint(Color.White)
    )
}