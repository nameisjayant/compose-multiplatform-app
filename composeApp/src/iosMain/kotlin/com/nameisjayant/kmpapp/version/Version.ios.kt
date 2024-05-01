package com.nameisjayant.kmpapp.version

import platform.UIKit.UIDevice

actual fun getPlatformVersion(): String = "IOS : ${UIDevice.currentDevice.systemVersion}"