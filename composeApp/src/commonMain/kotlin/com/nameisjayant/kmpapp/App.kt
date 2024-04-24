package com.nameisjayant.kmpapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.nameisjayant.kmpapp.jsonplaceholder.feature.ui.screens.PostScreen

@Composable
fun App() {
    MaterialTheme {
        PostScreen()
    }
}
