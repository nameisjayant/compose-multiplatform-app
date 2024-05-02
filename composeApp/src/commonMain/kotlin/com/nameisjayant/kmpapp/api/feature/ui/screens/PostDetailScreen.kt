package com.nameisjayant.kmpapp.api.feature.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
import com.nameisjayant.kmpapp.ui.interFont
import com.nameisjayant.kmpapp.utils.Constant
import com.nameisjayant.kmpapp.version.getPlatformName
import kmp_app_template.composeapp.generated.resources.Res
import kmp_app_template.composeapp.generated.resources.arrow
import kmp_app_template.composeapp.generated.resources.arrow_back
import kmp_app_template.composeapp.generated.resources.post_details
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource


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
                    imageVector = vectorResource(if (getPlatformName() == Constant.IOS) Res.drawable.arrow else Res.drawable.arrow_back),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(
                stringResource(Res.string.post_details), style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = interFont
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
                fontWeight = FontWeight.W700,
                fontFamily = interFont
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
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