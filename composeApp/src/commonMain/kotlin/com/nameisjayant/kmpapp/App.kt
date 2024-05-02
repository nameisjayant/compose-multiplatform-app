package com.nameisjayant.kmpapp

import androidx.compose.runtime.Composable
import com.nameisjayant.kmpapp.api.feature.navigation.PostNavigation
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.factory.withViewModelStoreOwner
import com.nameisjayant.kmpapp.imagePicker.screen.ShowImagesScreen
import com.nameisjayant.kmpapp.ui.KMPAppTemplate

@Composable
fun App() = withViewModelStoreOwner {
    KMPAppTemplate {
       //PostNavigation()
        ShowImagesScreen()
    }
}