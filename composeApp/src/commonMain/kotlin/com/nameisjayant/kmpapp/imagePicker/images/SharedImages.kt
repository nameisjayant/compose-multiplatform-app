package com.nameisjayant.kmpapp.imagePicker.images

import androidx.compose.ui.graphics.ImageBitmap

expect class SharedImages {
    fun toByteArray(): ByteArray?
    fun toImageBitmap(): ImageBitmap?
}