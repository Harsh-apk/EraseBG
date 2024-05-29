package com.harsh_kumar.erasebg.views

import android.content.Context
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.harsh_kumar.erasebg.R
import com.harsh_kumar.erasebg.models.ImageStateModel

import com.harsh_kumar.erasebg.viewModels.MainScreenViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(imageState: ImageStateModel, imageSelector:ActivityResultLauncher<String>,mainScreenViewModel: MainScreenViewModel,context: Context, _currentState: MutableState<ImageStateModel>){
    AnimatedContent(targetState = imageState,
        transitionSpec= {
            (slideInVertically { height -> height } + fadeIn()).with(slideOutVertically { height -> -height } + fadeOut())
        }
        ){

    if(it.imageBitmap!=null){
        FinalScreen(imageState = it,_currentState=_currentState)
    }else if(it.currentImage==null){
        InitialScreen(imageSelector)
    }else{
        SelectedImage(it,imageSelector,mainScreenViewModel,context)
    }
        }
}
@Composable
fun InitialScreen(imageSelector:ActivityResultLauncher<String>){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.imagecharacterui), contentDescription ="Character", modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 15.dp)
            .shadow(20.dp)
            .clip(
                RoundedCornerShape(20.dp)
            )
        )

        Text(text = "Erase Background", fontSize = TextUnit(24F, TextUnitType.Sp), modifier = Modifier.padding(top = 15.dp), fontWeight = FontWeight.Bold)
        Text(text="Separate subjects from backgrounds to use it as stickers or for other purposes.",fontSize=TextUnit(12F,TextUnitType.Sp), color = Color.Gray, modifier = Modifier.padding(horizontal = 30.dp, vertical = 5.dp), textAlign = TextAlign.Center)
        Button(onClick = {
            imageSelector.launch("image/*")
        }, modifier = Modifier.padding(vertical = 30.dp)) {
            Text(text = "Select Image")
        }

    }
}

@Composable
fun SelectedImage(imageState: ImageStateModel,imageSelector:ActivityResultLauncher<String>,mainScreenViewModel: MainScreenViewModel,context:Context){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(imageState.error!=null){
            Text(text = imageState.error, color = Color.Red, modifier = Modifier.padding(20.dp))
        }
        AsyncImage(model = imageState.currentImage, contentDescription = null, modifier = Modifier
            .padding(30.dp)
            .shadow(20.dp)
            .clip(
                RoundedCornerShape(50f)
            ))
        Button(onClick = { imageSelector.launch("image/*") }, modifier = Modifier.padding(top=20.dp)) {
            Text(text = "Reselect")
        }
        Button(onClick = {mainScreenViewModel.processImage(
            context
        ) }, modifier = Modifier.padding(top=5.dp)) {
            Text(text = "Erase Background")
        }

    }


}

@Composable
fun FinalScreen(imageState: ImageStateModel,_currentState: MutableState<ImageStateModel>){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        AsyncImage(model = imageState.imageBitmap, contentDescription =null,modifier = Modifier
//            .padding(30.dp)
//            .clip(
//                RoundedCornerShape(50f)
//            ))


            Image(
                bitmap = imageState.imageBitmap!!.asImageBitmap(),
                contentDescription = "some useful description",
                modifier = Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(20.dp))
            )



        Button(onClick = {
                         _currentState.value=_currentState.value.copy(
                             imageBitmap = null
                         )
        }, modifier = Modifier.padding(top=5.dp)) {
            Text(text = "Back")
        }



    }
}