package com.nameisjayant.kmpapp.version

import com.nameisjayant.kmpapp.utils.Constant
import platform.UIKit.UIDevice

actual fun getPlatformVersion(): String = "IOS : ${UIDevice.currentDevice.systemVersion}"
actual fun getPlatformName(): String = Constant.IOS