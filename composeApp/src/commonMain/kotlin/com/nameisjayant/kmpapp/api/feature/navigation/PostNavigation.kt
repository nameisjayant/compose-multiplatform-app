package com.nameisjayant.kmpapp.api.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nameisjayant.kmpapp.api.feature.ui.screens.PostDetailScreen
import com.nameisjayant.kmpapp.api.feature.ui.screens.PostScreen
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.PostViewModel
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.factory.viewModels


@Composable
fun PostNavigation() {

    val navHostController = rememberNavController()
    val viewModel = viewModels(modelClass = PostViewModel::class)

    NavHost(navController = navHostController, startDestination = PostRoutes.Posts) {
        composable(PostRoutes.Posts) {
            PostScreen(viewModel, navHostController)
        }
        composable(PostRoutes.PostDetails) {
            PostDetailScreen(viewModel, navHostController)
        }
    }

}

object PostRoutes {
    const val Posts = "/posts"
    const val PostDetails = "/postDetails"
}