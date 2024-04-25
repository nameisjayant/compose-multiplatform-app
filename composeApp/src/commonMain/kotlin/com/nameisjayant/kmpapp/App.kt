package com.nameisjayant.kmpapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.nameisjayant.kmpapp.api.feature.ui.screens.PostScreen
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.factory.withViewModelStoreOwner

@Composable
fun App() = withViewModelStoreOwner {
    MaterialTheme {
        PostScreen()
    }
}