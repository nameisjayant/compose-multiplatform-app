package com.nameisjayant.kmpapp.api.feature.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.nameisjayant.kmpapp.api.data.modal.Post
import com.nameisjayant.kmpapp.api.feature.navigation.PostRoutes
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.PostViewModel
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.events.PostEvent
import com.nameisjayant.kmpapp.ui.interFont
import kmp_app_template.composeapp.generated.resources.Res
import kmp_app_template.composeapp.generated.resources.posts
import org.jetbrains.compose.resources.stringResource


@Composable
fun PostScreen(
    viewModel: PostViewModel,
    navHostController: NavHostController
) {
    val response by viewModel.postEventFlow.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    LifecycleEvent(lifecycleOwner) {
        viewModel.onEvent(PostEvent.GetPostEvent)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                stringResource(Res.string.posts), style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = interFont
                )
            )
        }
        if (response.data.isNotEmpty()) {
            items(response.data, key = { it.id ?: it.hashCode() }) {
                PostEachRow(data = it) {
                    viewModel.setPostData(it)
                    navHostController.navigate(PostRoutes.PostDetails)
                }
            }
        }
        if (response.isLoading)
            item {
                CircularProgressIndicator(color = Color.Black)
            }
        if (response.error.isNotEmpty())
            item {
                Text(response.error)
            }
    }
}

@Composable
private fun LifecycleEvent(
    lifecycleOwner: LifecycleOwner,
    onEvent: () -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    onEvent()
                }

                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
private fun PostEachRow(
    modifier: Modifier = Modifier,
    data: Post,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(top = 20.dp).clickable {
            onClick()
        }
    ) {
        Text(
            "${data.title}", style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                fontFamily = interFont
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            "${data.body}", style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
                fontFamily = interFont
            )
        )
    }
}