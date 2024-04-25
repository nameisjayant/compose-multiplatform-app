package com.nameisjayant.kmpapp.imagePicker.permission

import androidx.compose.runtime.Composable


enum class PermissionStatus {
    GRANTED,
    DENIED,
    SHOW_RATIONAL
}

enum class PermissionType {
    CAMERA,
    GALLERY
}

interface PermissionHandler {
    @Composable
    fun askPermission(permission: PermissionType)

    @Composable
    fun isPermissionGranted(permission: PermissionType): Boolean

    @Composable
    fun launchSettings()

}

interface PermissionCallback {
    fun onPermissionStatus(permissionType: PermissionType, permissionStatus: PermissionStatus)
}

expect class PermissionManager(callback:PermissionCallback):PermissionHandler

@Composable
expect fun createPermissionsManager(callback: PermissionCallback): PermissionManager