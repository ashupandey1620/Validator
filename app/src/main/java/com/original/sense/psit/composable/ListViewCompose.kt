package com.original.sense.psit.composable

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
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
              navController: NavController
              ) {

    val tokenStoreViewModel: TokenStoreViewModel = hiltViewModel()

    val accessToken by tokenStoreViewModel.readAccess.collectAsState()

    var isLoading by remember { mutableStateOf(true) }

    accessToken?.let { str ->
        access = accessToken.toString()
    }

    LaunchedEffect(Unit) {
        delay(2000)
        isLoading = false
    }

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f) ,
        Color.LightGray.copy(alpha = 0.2f) ,
        Color.LightGray.copy(alpha = 0.6f) ,
    )

    val transition = rememberInfiniteTransition()

    val translateAnim = transition.animateFloat(
        initialValue = 0f ,
        targetValue = 1000f ,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000 ,
                easing = FastOutSlowInEasing
            )
        ) , label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors ,
        start = Offset.Zero ,
        end = Offset(x = translateAnim.value , y = translateAnim.value)
    )

    LaunchedEffect(Unit) {
        if (accessToken != null && isLoading) {
            val accessTokenValue = accessToken ?: return@LaunchedEffect
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically ,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {

        val paddingModifier = Modifier.padding(10.dp)
        Card(
            elevation = CardDefaults.cardElevation(5.dp) , modifier = paddingModifier,
            shape = RoundedCornerShape(24.dp) ,
            colors = CardDefaults.cardColors(Color(0xFF383841)),
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

                Column(modifier = Modifier.padding(vertical = 5.dp)) {

                     if(model.name!="Incorrect Student Id"&&model.name!="Internal Server Error"&&model.name!="Network Error"){
                    Text( modifier = Modifier.clickable {
                            navController.navigate("detailedScreen" + "/${model.rollNum}"+"/${model.name}") },
                            text = model.name ,
                            fontSize = 14.sp ,
                            fontWeight = FontWeight.ExtraBold ,
                            color = Color.White ,
                            fontFamily = poppins
                        )
                    Text(
                        modifier = Modifier.clickable { 
                            navController.navigate("detailedScreen" + "/${model.rollNum}"+"/${model.name}")
                        },
                        text = model.rollNum.toString(),
                        fontSize = 14.sp ,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        fontFamily = poppins
                    )}
                    else{
                         Text(
                             text = model.name ,
                             fontSize = 14.sp ,
                             fontWeight = FontWeight.ExtraBold ,
                             color = when (model.name) {
                                 "Incorrect Student Id" -> { Color.Red}
                                 "Internal Server Error" -> {Color.Yellow}
                                 else -> {Color.Magenta}
                             } ,
                             fontFamily = poppins
                         )
                         Text(
                             text = model.rollNum.toString(),
                             fontSize = 14.sp ,
                             fontWeight = FontWeight.Light,
                             color = when (model.name) {
                                 "Incorrect Student Id" -> { Color.Red}
                                 "Internal Server Error" -> {Color.Yellow}
                                 else -> {Color.Magenta}
                             },
                             fontFamily = poppins
                         )

                     }
                }


            }
            }
        }
    }




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListDemo(
    selectedItems: MutableList<PersonModel> ,
    studentList: SnapshotStateList<PersonModel> = mutableStateListOf<PersonModel>() ,
    navController: NavController
) {

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
                    navController
                )
            }
        }
        Spacer(modifier = Modifier.padding(horizontal = 50.dp))
    }
}


