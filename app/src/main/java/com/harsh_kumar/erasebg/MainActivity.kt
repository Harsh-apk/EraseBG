package com.harsh_kumar.erasebg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentation
import com.harsh_kumar.erasebg.ui.theme.EraseBGTheme
import com.harsh_kumar.erasebg.viewModels.MainScreenViewModel
import com.harsh_kumar.erasebg.views.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainScreenViewModel: MainScreenViewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        val imageState by mainScreenViewModel.currentState
        val imageSelector = registerForActivityResult(ActivityResultContracts.GetContent()){
            uri-> mainScreenViewModel._currentState.value = mainScreenViewModel._currentState.value.copy(
                currentImage = uri
            )
        }

//make enter screen UI
        //select image button
        // get file url
        //open image image processor - subject segmentation and get result
        // display result using compose -> use ViewModel for file url and image bitmap
        enableEdgeToEdge()
        setContent {
            EraseBGTheme {
               MainScreen(imageState = imageState,imageSelector=imageSelector,mainScreenViewModel=mainScreenViewModel, context = this)
            }
        }
    }
}

