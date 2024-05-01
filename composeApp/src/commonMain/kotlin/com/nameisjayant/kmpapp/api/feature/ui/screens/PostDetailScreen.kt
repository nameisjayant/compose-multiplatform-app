package com.nameisjayant.kmpapp.api.feature.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nameisjayant.kmpapp.api.data.modal.Post
import com.nameisjayant.kmpapp.api.feature.ui.viewmodel.PostViewModel


@Composable
fun PostDetailScreen(
    viewModel: PostViewModel,
    navHostController: NavHostController
) {
    val response by viewModel.postData.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                navHostController.navigateUp()
            }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "Posts Detail", style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
        }
        response?.let {
           Column(
               modifier = Modifier.padding(horizontal = 10.dp)
           ) {
               PostData(data = it)
           }
        }
    }

}

@Composable
private fun PostData(
    modifier: Modifier = Modifier,
    data: Post
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(top = 20.dp)
    ) {
        Text(
            "${data.id}", style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            "${data.title}", style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            "${data.body}", style = TextStyle(
                color = Color.Gray,
                fontSize = 14.sp,
            )
        )
    }
}