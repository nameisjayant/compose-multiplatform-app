package com.nameisjayant.kmpapp.imagePicker.images

import androidx.compose.runtime.Composable

@Composable
expect fun rememberGalleryManager(onResult: (SharedImages?) -> Unit): GalleryManager


expect class GalleryManager(
    onLaunch: () -> Unit
) {
    fun launch()
}