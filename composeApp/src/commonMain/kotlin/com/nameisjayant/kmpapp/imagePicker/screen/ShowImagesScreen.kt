package com.nameisjayant.kmpapp.imagePicker.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.AlertDialog
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.nameisjayant.kmpapp.imagePicker.permission.PermissionCallback
import com.nameisjayant.kmpapp.imagePicker.permission.PermissionStatus
import com.nameisjayant.kmpapp.imagePicker.permission.PermissionType
import com.nameisjayant.kmpapp.imagePicker.permission.createPermissionsManager


@Composable
fun ShowImagesScreen() {
    var isDialog by remember { mutableStateOf(false) }
    var openGallery by remember { mutableStateOf(false) }
    var openCamera by remember { mutableStateOf(false) }
    val permissionManager = createPermissionsManager(object : PermissionCallback {
        override fun onPermissionStatus(
            permissionType: PermissionType,
            permissionStatus: PermissionStatus
        ) {
            when (permissionStatus) {
                PermissionStatus.GRANTED -> {
                    when (permissionType) {
                        PermissionType.CAMERA -> openCamera = true
                        PermissionType.GALLERY -> openGallery = true
                    }
                }

                else -> {

                }
            }
        }
    })
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isDialog = true
            }) {
                Icon(Icons.Default.Add, tint = Color.White, contentDescription = null)
            }
        }
    ) {

    }

    if (isDialog)
        ChooseDialog(onValueUpdate = {
            isDialog = it
        }, onGalleryClick = {}) {}
}

@Composable
fun ChooseDialog(
    modifier: Modifier = Modifier,
    onValueUpdate: (Boolean) -> Unit,
    onGalleryClick: (Int) -> Unit,
    onCameraClick: (Int) -> Unit
) {

    AlertDialog(onDismissRequest = {
        onValueUpdate(false)
    }, buttons = {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ClickableText(AnnotatedString("Gallery"), onClick = onGalleryClick)
            Spacer(modifier = Modifier.height(16.dp))
            ClickableText(AnnotatedString("Camera"), onClick = onCameraClick)
        }
    }, modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), title = {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text("Choose from")
        }
    })

}