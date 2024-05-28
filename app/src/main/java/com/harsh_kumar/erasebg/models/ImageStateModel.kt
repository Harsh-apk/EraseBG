package com.harsh_kumar.erasebg.models

import android.graphics.Bitmap
import android.net.Uri

data class ImageStateModel (
    val currentImage: Uri? = null,
    val error: String? = null,
    val imageBitmap: Bitmap? = null,

)