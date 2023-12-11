package com.original.sense.psit.composable

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.original.sense.psit.ViewModels.PsitViewModel
import com.original.sense.psit.ViewModels.TokenStoreViewModel
import com.original.sense.psit.model.PersonModel
import com.original.sense.psit.model.PostModel.GetStudentPost
import com.original.sense.psit.screens.access
import com.original.sense.psit.ui.theme.poppins
import kotlinx.coroutines.delay


@Composable
fun ListItem( model: PersonModel,
              isSelected: Boolean,
              onItemSelected: (Boolean) -> Unit,
              ) {
    var studentName by remember {
        mutableStateOf("")
    }

    val psitViewModel : PsitViewModel = hiltViewModel()

    val tokenStoreViewModel : TokenStoreViewModel = hiltViewModel()

    val accessToken by tokenStoreViewModel.readAccess.collectAsState()

    val getStudentResponse by psitViewModel.getStudent.observeAsState()

    var isLoading by remember { mutableStateOf(true) }



    accessToken?.let { str ->
        access = accessToken.toString()
    }



    // Simulating data loading delay with a coroutine
    LaunchedEffect(Unit) {
        // Simulate a delay of data loading
        delay(2000) // Adjust the delay time as per your requirement

        // After the delay, set isLoading to false to indicate data loading is complete
        isLoading = false
    }



    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f ,
        targetValue =  1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ) , label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x=translateAnim.value,y=translateAnim.value)
    )

    LaunchedEffect(Unit) {
        if (isLoading) {
            val accessTokenValue = accessToken ?: return@LaunchedEffect
            val getStudentPost = GetStudentPost(model.rollNum)
            psitViewModel.getStudent(accessTokenValue, getStudentPost)
        }
    }

    getStudentResponse?.let { response ->
        Log.d("Get student" , "$response")
        Log.d("Get student errors" , "${response.errors}")
//        Log.d("Get Student Message" , "${response.message}")
//        Log.d("Get Student msgs" , "${response.message?.messages}")
//        Log.d("Get Student code" , response.message.code)
//        Log.d("Get Student details" , response.message.detail)

        Row(
            verticalAlignment = Alignment.CenterVertically ,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {

            val paddingModifier = Modifier.padding(10.dp)
            Card(
                elevation = CardDefaults.cardElevation(5.dp) , modifier = paddingModifier ,
                shape = RoundedCornerShape(24.dp) ,
                colors = CardDefaults.cardColors(Color(0xFF383841))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp) ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isSelected ,
                        onCheckedChange = { checked ->
                            onItemSelected(checked)

                        } ,
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = Color.White ,
                            checkedColor = Color.Red ,
                            uncheckedColor = Color.White
                        )
                    )

                    Column(modifier = Modifier.padding(vertical = 10.dp)) {

                        if (isLoading) {
                            // Render shimmering effect or loading placeholder
                            Spacer(
                                modifier = Modifier
                                    .height(28.dp)
                                    .fillMaxWidth(0.9f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(brush)
                            )
                        } else {
                            Text(
                                text = response.responseData.name ,
                                fontSize = 18.sp ,
                                fontWeight = FontWeight.SemiBold ,
                                color = Color.White ,
                                fontFamily = poppins
                            )
                        }

                        Spacer(modifier = Modifier.padding(3.dp))



                        Text(
                            text = model.rollNum.toString() ,
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
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListDemo(
    selectedItems: MutableList<PersonModel>,
    studentList: SnapshotStateList<PersonModel> = mutableStateListOf<PersonModel>()  ) {

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
                ListItem(
                    model = model,
                    isSelected = selectedItems.contains(model),
                    onItemSelected = { selected ->
                        if (selected) {
                            selectedItems.add(model)
                        } else {
                            selectedItems.remove(model)
                        }
                    },

                )
            }
        }

        Spacer(modifier = Modifier.padding(horizontal = 50.dp))
    }
}


