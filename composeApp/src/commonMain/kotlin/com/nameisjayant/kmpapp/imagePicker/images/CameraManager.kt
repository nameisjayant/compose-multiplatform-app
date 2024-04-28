package com.nameisjayant.kmpapp.imagePicker.images

import androidx.compose.runtime.Composable

@Composable
expect fun rememberCameraManager(onResult: (SharedImages?) -> Unit): CameraManager


expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}