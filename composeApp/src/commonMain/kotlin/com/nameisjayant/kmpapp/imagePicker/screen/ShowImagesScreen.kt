package com.nameisjayant.kmpapp.imagePicker.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.kmpapp.imagePicker.images.rememberCameraManager
import com.nameisjayant.kmpapp.imagePicker.images.rememberGalleryManager
import com.nameisjayant.kmpapp.imagePicker.permission.PermissionCallback
import com.nameisjayant.kmpapp.imagePicker.permission.PermissionStatus
import com.nameisjayant.kmpapp.imagePicker.permission.PermissionType
import com.nameisjayant.kmpapp.imagePicker.permission.createPermissionsManager
import com.nameisjayant.kmpapp.ui.interFont
import kmp_app_template.composeapp.generated.resources.Res
import kmp_app_template.composeapp.generated.resources.alert
import kmp_app_template.composeapp.generated.resources.camera
import kmp_app_template.composeapp.generated.resources.cancel
import kmp_app_template.composeapp.generated.resources.choose_from
import kmp_app_template.composeapp.generated.resources.gallery
import kmp_app_template.composeapp.generated.resources.grant_permission
import kmp_app_template.composeapp.generated.resources.image_picker_app
import kmp_app_template.composeapp.generated.resources.open_setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.stringResource


@Composable
fun ShowImagesScreen() {
    val coroutineScope = rememberCoroutineScope()
    val imageBitmap = remember {
        mutableStateListOf<ImageBitmap?>()
    }
    var isDialog by remember { mutableStateOf(false) }
    var openGallery by remember { mutableStateOf(false) }
    var openCamera by remember { mutableStateOf(false) }
    var permissionRationalDialog by remember { mutableStateOf(false) }
    var launchSetting by remember { mutableStateOf(value = false) }
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
                    permissionRationalDialog = true
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
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(Res.string.image_picker_app), style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = interFont
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            if (imageBitmap.toList().isNotEmpty()) {
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(imageBitmap.toList()) {
                        it?.let {
                            Image(
                                bitmap = it, contentDescription = null,
                                modifier = Modifier.size(130.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }


    }

    val cameraManager = rememberCameraManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                it?.toImageBitmap()
            }
            isDialog = false
            bitmap?.let {
                imageBitmap.add(it)
            }

        }
    }

    val galleryManager = rememberGalleryManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                it?.toImageBitmap()
            }
            isDialog = false
            bitmap?.let {
                imageBitmap.add(it)
            }
        }
    }

    if (isDialog)
        ChooseDialog(onValueUpdate = {
            isDialog = it
        }, onGalleryClick = {
            openGallery = true
        }) {
            openCamera = true
        }

    if (openCamera) {
        if (permissionManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionManager.askPermission(PermissionType.CAMERA)
        }
        openCamera = false
    }
    if (openGallery) {
        if (permissionManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionManager.askPermission(PermissionType.GALLERY)
        }
        openGallery = false
    }
    if (permissionRationalDialog) {
        OpenSettingDialog(
            onDialogValueUpdated = {},
            onCancel = {
                permissionRationalDialog = false
            },
            onOpenSetting = {
                launchSetting = true
                permissionRationalDialog = false
            }
        )
    }
    if (launchSetting) {
        permissionManager.launchSettings()
        launchSetting = false
    }
}

@Composable
private fun ChooseDialog(
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
            ClickableText(
                AnnotatedString(stringResource(Res.string.gallery)),
                onClick = onGalleryClick,
                style = TextStyle(
                    fontFamily = interFont
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            ClickableText(
                AnnotatedString(stringResource(Res.string.camera)),
                onClick = onCameraClick,
                style = TextStyle(
                    fontFamily = interFont
                )
            )
        }
    }, modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp), title = {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(Res.string.choose_from), fontFamily = interFont)
        }
    })
}

@Composable
private fun OpenSettingDialog(
    modifier: Modifier = Modifier,
    onDialogValueUpdated: (Boolean) -> Unit,
    onCancel: () -> Unit,
    onOpenSetting: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDialogValueUpdated(false) }, buttons = {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Button(
                onClick = onCancel,
                modifier = Modifier.weight(0.5f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(Res.string.cancel), fontFamily = interFont)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = onOpenSetting, modifier = Modifier.weight(0.5f)) {
                Text(stringResource(Res.string.open_setting), fontFamily = interFont)
            }
        }
    }, modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(Res.string.alert), fontFamily = interFont)
            }
        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(Res.string.grant_permission),
                    textAlign = TextAlign.Center,
                    fontFamily = interFont
                )
            }
        }
    )
}