package com.harsh_kumar.erasebg.viewModels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentation
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenterOptions
import com.harsh_kumar.erasebg.models.ImageStateModel

class MainScreenViewModel: ViewModel() {
//    val _currentImage: MutableState<Uri?> = mutableStateOf(null)
//    val currentImage: State<Uri?> = _currentImage
    val _currentState: MutableState<ImageStateModel> = mutableStateOf(ImageStateModel())
    val currentState: State<ImageStateModel> = _currentState

//    fun createInputImage(context:Context):InputImage{
//        return InputImage.fromFilePath(context,currentImage.value!!)
//
//    }
//    fun createInstance(){
//        val opt
//    }
    fun processImage(context: Context){
        val inputImage = InputImage.fromFilePath(context,currentState.value.currentImage!!)
        val options = SubjectSegmenterOptions.Builder()
        .enableForegroundBitmap()
        .build()
        val segmenter = SubjectSegmentation.getClient(options)
        segmenter.process(inputImage)
            .addOnSuccessListener {result->
                val foregroundBitmap = result.foregroundBitmap
                _currentState.value = _currentState.value.copy(
                    imageBitmap = foregroundBitmap
                )
            }.addOnFailureListener{
                _currentState.value = _currentState.value.copy(
                    error = it.message
                )
            }
    }
}